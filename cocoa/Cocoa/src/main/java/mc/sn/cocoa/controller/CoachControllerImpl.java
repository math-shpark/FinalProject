package mc.sn.cocoa.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import mc.sn.cocoa.service.CoachService;
import mc.sn.cocoa.vo.MemberVO;

@Controller("coachController")
public class CoachControllerImpl implements CoachController {

	// 저장되는 경로 
	private static final String COACH_IMAGE_REPO = "C:\\cocoa\\coach_image";
	@Autowired
	private CoachService coachService;

	// 코치 글 작성 창으로 이동
	@Override
	@RequestMapping(value = "/view_coachWrite", method = RequestMethod.GET)
	public ModelAndView view_coachWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/coachWriteForm";
		mav.setViewName(url);
		return mav;
	}

	// 코치 글 작성
	@Override
	@RequestMapping(value = "/coachWrite", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewCoach(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		
		multipartRequest.setCharacterEncoding("utf-8");
		
		// DB에 담을 코치 글 정보
		Map<String, Object> coachMap = new HashMap<String, Object>();
		
		// 받아온 정보들을 coachMap에 [key & value]로 설정
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			coachMap.put(name, value);
		}

		// 세션 불러오기
		HttpSession session = multipartRequest.getSession();
		
		// memberVO로 세션에 저장된 로그인한 회원의 정보를 저장
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		
		// coachWriteForm에 존재하지 않는 id 직접 입력
		String id = memberVO.getId();
		coachMap.put("coach", id);
		// coachWriteForm에 불러온 파일(이미지) 직접 입력
		String cImg = this.upload(multipartRequest);
		coachMap.put("cImg", cImg);

		// 성공, 실패 시 알림
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		
		try {
			// 다운로드 파일을 작성하는 회원의 id(coach)로 폴더 생성
			// 삭제가 되어도 그 후에 해당 회원이 업로드를 하면 같은 폴더에 생성이 됌
			// 삭제 로직 짤때 고려해야함
			int coachNO = coachService.addNewCoach(coachMap);
			if (cImg != null && cImg.length() != 0) {
				File srcFile = new File(COACH_IMAGE_REPO + "\\" + "temp" + "\\" + cImg);
				File destDir = new File(COACH_IMAGE_REPO + "\\" + id + "\\" + coachNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('등록이 완료되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			// 예외발생시 취소 및 삭제
			File srcFile = new File(COACH_IMAGE_REPO + "\\" + "temp" + "\\" + cImg);
			srcFile.delete();

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해주세요.');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// 파일 업로드
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		String cImg = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			cImg = mFile.getOriginalFilename();
			File file = new File(COACH_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					if (file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(COACH_IMAGE_REPO + "\\" + "temp" + "\\" + cImg));
			}
		}
		return cImg;
	}
}