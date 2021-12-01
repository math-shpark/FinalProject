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

import mc.sn.cocoa.service.ProjectService;
import mc.sn.cocoa.vo.MemberVO;

@Controller("projectController")
public class ProjectControllerImpl implements ProjectController {
	private static final String PROJECT_IMAGE_REPO = "C:\\cocoa\\project_image";
	@Autowired
	private ProjectService projectService;

	// 프로젝트 작성 화면
	@Override
	@RequestMapping(value = "/view_projectWrite", method = RequestMethod.GET)
	public ModelAndView view_join(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/projectWriteForm";
		mav.setViewName(url);
		return mav;
	}

	// 프로젝트 작성
	@Override
	@RequestMapping(value = "/projectWrite", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewProject(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> projectMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			projectMap.put(name, value);
		}

		String pImg = upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String id = memberVO.getId();
		projectMap.put("leader", id);
		projectMap.put("pImg", pImg);

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int projectNO = projectService.addProject(projectMap);
			if (pImg != null && pImg.length() != 0) {
				File srcFile = new File(PROJECT_IMAGE_REPO + "\\" + "temp" + "\\" + pImg);
				File destDir = new File(PROJECT_IMAGE_REPO + "\\" + projectNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('등록이 완료되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			File srcFile = new File(PROJECT_IMAGE_REPO + "\\" + "temp" + "\\" + pImg);
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
		String pImg = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			pImg = mFile.getOriginalFilename();
			File file = new File(PROJECT_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					if (file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(PROJECT_IMAGE_REPO + "\\" + "temp" + "\\" + pImg));
			}
		}
		return pImg;
	}
}
