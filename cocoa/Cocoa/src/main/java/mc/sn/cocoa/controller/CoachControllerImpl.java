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
		Map<String, Object> coachMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			coachMap.put(name, value);
		}

		String cImg = upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		// 로그인이 안 되어 있을시 뭔가 조치가 필요
		String id = memberVO.getId();
		coachMap.put("coach", id);
		coachMap.put("cImg", cImg);

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int coachNO = coachService.addNewCoach(coachMap);
			if (cImg != null && cImg.length() != 0) {
				File srcFile = new File(COACH_IMAGE_REPO + "\\" + "temp" + "\\" + cImg);
				File destDir = new File(COACH_IMAGE_REPO + "\\" + coachNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('등록이 완료되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
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

	// 파일 업로드 = 파일에 파일명 존재시 충돌, (MultiPartFile-multiPartResolver)
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