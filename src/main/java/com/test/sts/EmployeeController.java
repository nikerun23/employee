package com.test.sts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/* @Controller 어노테이션은 현재 클래스를 SpringWebMVC가 관리하는 컨트롤러로 등록할 때 사용 */
@Controller
public class EmployeeController {

	// @Autowired 어노테이션을 이용한 자동 의존 주입
	// DAO 객체에 대한 의존 주입
	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private RegionDAO regionDAO;
	@Autowired
	private DepartmentDAO departmentDAO;
	@Autowired
	private PositionDAO positionDAO;
	
	@Autowired
	private ServletContext context;
	
	/* @RequestMapping 어노테이션에서 method 속성은 요청 방식(GET or POST)을 분석할 때 사용 */
	/* @RequestMapping 어노테이션과 연결된 메소드만 서블릿 요청에 대한 응답 메소드가 될 수 있다. */
	/* 응답 메소드의 매개 변수 지정은 SpringWebMVC에 의해 자동 분석되기 때문에, 필요한 객체를 요청할 때 사용한다. */
	/*
	 * method = {RequestMethod.GET, RequestMethod.POST}는 GET, POST 방식 두 가지 모두 수신 가능
	 */
	// 직원 명단  전체 및 검색 출력용 메소드
	@RequestMapping(value = "/employeelist.it", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeelist(ModelMap model, String key, String value) {

		// 검색 요청 데이터 수신 처리 -> 스프링이 자동 수신 (자료형 클래스 준비 or 멤버 변수 준비)
		List<Employee> list = null;
		int totalcount = 0;
		int count = 0;

		if (key == null) {
			key = "all";
			value = "";
		} else {
			// 검색 option 선택
			String[] str = {"","employeeId","name","regionName","departmentName","positionName"};
			key = str[Integer.parseInt(key)];
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("value", value);
		
		list = employeeDAO.employeeList(map);
		totalcount = employeeDAO.totalCount();
		count = list.size();
		
		/* 서블릿 액션의 결과를 JSP 페이지(View)에 전달하는 경우 Model 객체를 사용한다. */
		model.addAttribute("list", list);
		model.addAttribute("totalcount", totalcount);
		model.addAttribute("count", count);
		model.addAttribute("skey", key);
		model.addAttribute("svalue", value);
		
		/* View 정보를 반환하는 부분 */
		return "employeelist"; // "/WEB-INF/views/employeelist.jsp"
	}
	// 사용자용 nemployeelist
	@RequestMapping(value = "/nemployeelist.it", method = { RequestMethod.GET, RequestMethod.POST })
	public String nemployeelist(ModelMap model, String key, String value) {
		// 검색 요청 데이터 수신 처리 -> 스프링이 자동 수신 (자료형 클래스 준비 or 멤버 변수 준비)
		List<Employee> list = null;
		int totalcount = 0;
		int count = 0;

		if (key == null) {
			key = "all";
			value = "";
		} else {
			// 검색 option 선택
			String[] str = {"","employeeId","name","regionName","departmentName","positionName"};
			key = str[Integer.parseInt(key)];
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("value", value);
		
		list = employeeDAO.employeeList(map);
		totalcount = employeeDAO.totalCount();
		count = list.size();
		
		/* 서블릿 액션의 결과를 JSP 페이지(View)에 전달하는 경우 Model 객체를 사용한다. */
		model.addAttribute("list", list);
		model.addAttribute("totalcount", totalcount);
		model.addAttribute("count", count);
		model.addAttribute("skey", key);
		model.addAttribute("svalue", value);
		
		/* View 정보를 반환하는 부분 */
		return "nemployeelist"; // "/WEB-INF/views/nemployeelist.jsp"
	}
	
	
	@RequestMapping(value = "/employeeinsertform.it", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeinsertform(ModelMap model) {
		List<Region> regionList = regionDAO.regionList();
		List<Department> departmentList = departmentDAO.departmentList();
		List<Position> positionList = positionDAO.positionList();
		
		model.addAttribute("regionList", regionList);
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("positionList", positionList);
		
		return "employeeinsertform"; // "/WEB-INF/views/employeeinsertform.jsp"
	}
	
	// 직원 정보 추가
	@RequestMapping(value = "/employeeinsert.it", method = RequestMethod.POST)
	public String employeeinsert(Employee emp) {
		employeeDAO.employeeAdd(emp);
		
		/* View 정보를 반환하는 부분 */
		return "redirect:employeelist.it";
	}
	
	// 직원 정보 수정
	@RequestMapping(value = "/employeeupdateform.it", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeupdateform(ModelMap model, String employeeId) {
		List<Employee> list = null;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "1");
		map.put("value", employeeId);
		
		list = employeeDAO.employeeList(map);
		Employee emp = list.get(0);
		List<Region> regionList = regionDAO.regionList();
		List<Department> departmentList = departmentDAO.departmentList();
		List<Position> positionList = positionDAO.positionList();
		
		model.addAttribute("emp", emp);
		model.addAttribute("regionList", regionList);
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("positionList", positionList);
		
		/* View 정보를 반환하는 부분 */
		return "employeeupdateform";
	}
	
	// 사진 정보 검색 출력용
	@RequestMapping(value = "/ajaxpicture.it", method = RequestMethod.POST)
	public String ajaxpicture(ModelMap model, Employee emp) {
		String result = null;
		List<Employee> list = employeeDAO.pictureList(emp);
		result = list.get(0).getEmployeePicFileName();
		model.addAttribute("result", result);
		
		/* View 정보를 반환하는 부분 */
		return "ajaxpicture";
	}
	
	// 직원 사진 추가 및 변경
	@RequestMapping(value = "/employeepictureinsert.it", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeepictureinsert(Employee emp, BindingResult result) throws IOException {
		System.out.println("employeepictureinsert 메소드 호출");

		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "redirect:fileuploaderror.it";
		} else {
			System.out.println("file.getFile()="+emp.getFile());
			//업로된 파일에 대한 객체 정보
			MultipartFile multipartFile = emp.getFile();

			System.out.println("multipartFile="+multipartFile);
			// Create a folder picture under WebContent sub-folder.
			// 주의) ServletContext 객체를 이용한 경로명 확보 필수
			String uploadPath = context.getRealPath("") + "resources/picture" + File.separator;
			// 파일 중복 검사 과정 추가 or 임의의 파일명 동적 생성 -> 사용자 직접 작성
			String newFileName = FileRenamePolicy.rename(multipartFile.getOriginalFilename());
			// 파일 타입 확인
			String contentType = multipartFile.getContentType();
			// 파일 사이즈 확인
			long fileSize = multipartFile.getSize();
			// 사진 -> image/jpeg, image/png
			if ((!contentType.equals("image/jpeg") || !contentType.equals("image/png")) && fileSize >= 1024 * 500) {
				return "redirect:fileuploaderror.it";
			}
			
			// 기존 사진 저장여부 검사
			List<Employee> list = employeeDAO.pictureList(emp);
			if (list.size() > 0) {
				// 사진이 존재하면 삭제 진행
				String picFileName = list.get(0).getEmployeePicFileName();
				// 파일 삭제
				pictureDelete(uploadPath, picFileName);
				// 새로운 사진 업로드 액션
				FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + newFileName));
				// 새로 업로드한 파일명으로 DB 저장
				emp.setEmployeePicFileName(newFileName);
				// ID와 변경할 이미지 파일명이 담겨져 있다.
				employeeDAO.pictureModify(emp);
				
				return "redirect:employeelist.it";// "WEB-INF/source/employeelist.jsp"
			}
			
			// 파일 업로드 액션
			FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + newFileName));

			// DB에 사진 정보 저장
			emp.setEmployeePicFileName(newFileName);
			
			employeeDAO.pictureAdd(emp);
			
			return "redirect:employeelist.it";// "WEB-INF/source/employeelist.jsp"
		}
	}
	
	// 사진 파일 삭제
	private Boolean pictureDelete(String path, String fileName) {
		boolean result = false;
		// 기존 파일이 삭제되면 true 리턴
		result = new File(path+fileName).delete();
		return result; 
	}
	
	// 최소기본급 검색
	@RequestMapping(value = "/ajaxminbasicpay.it", method = RequestMethod.POST)
	public String ajaxminbasicpay(ModelMap model, Position p) {
		String result = "0";
		result = String.valueOf(positionDAO.getMinBasicPay(p));
		
		model.addAttribute("result", result);
		
		/* View 정보를 반환하는 부분 */
		return "ajaxminbasicpay";
	}
	
	// 로그인
	@RequestMapping(value = "/loginform.it", method = RequestMethod.GET)
	public String loginform() {
		
		/* View 정보를 반환하는 부분 */
		return "loginform";
	}
	
	// 로그아웃
	@RequestMapping(value = "/logoutform.it", method = RequestMethod.GET)
	public String logoutform() {
		
		/* View 정보를 반환하는 부분 */
		return "logoutform";
	}
	
	// 로그인실패
	@RequestMapping(value = "/loginfail.it", method = RequestMethod.GET)
	public String loginfail() {
		
		/* View 정보를 반환하는 부분 */
		return "loginfail";
	}
	
	// 계정별 로그인
	@RequestMapping(value = "/loginresult.it", method = RequestMethod.GET)
	public String loginresult(SecurityContextHolderAwareRequestWrapper request) {
		
		/* View 정보를 반환하는 부분 */
		/*로그인한 계정의 Roll이 ROLE_ADMIN이면 employeelist.it로 이동*/
		String result= "";
		if (request.isUserInRole("ROLE_ADMIN")) {
			result = "redirect:employeelist.it";
		} else {
			result = "redirect:nemployeelist.it";
		}
		return result;
	}
	
}