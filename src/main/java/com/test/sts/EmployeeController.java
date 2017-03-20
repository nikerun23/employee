package com.test.sts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	/* @RequestMapping 어노테이션에서 method 속성은 요청 방식(GET or POST)을 분석할 때 사용 */
	/* @RequestMapping 어노테이션과 연결된 메소드만 서블릿 요청에 대한 응답 메소드가 될 수 있다. */
	/* 응답 메소드의 매개 변수 지정은 SpringWebMVC에 의해 자동 분석되기 때문에, 필요한 객체를 요청할 때 사용한다. */
	/*
	 * method = {RequestMethod.GET, RequestMethod.POST}는 GET, POST 방식 두 가지 모두 수신 가능
	 */
	@RequestMapping(value = "/employeelist", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeelist(ModelMap model, String skey, String svalue) {

		// 검색 요청 데이터 수신 처리 -> 스프링이 자동 수신 (자료형 클래스 준비 or 멤버 변수 준비)

		List<Employee> list = null;
		int totalcount = 0;
		int count = 0;

		if (skey == null) {
			skey = "all";
			svalue = "";
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", skey);
		map.put("value", svalue);
		
		list = employeeDAO.employeeList(map);
		totalcount = employeeDAO.totalCount();
		count = list.size();
		
		/* 서블릿 액션의 결과를 JSP 페이지(View)에 전달하는 경우 Model 객체를 사용한다. */
		model.addAttribute("list", list);
		model.addAttribute("totalcount", totalcount);
		model.addAttribute("count", count);
		model.addAttribute("skey", skey);
		model.addAttribute("svalue", svalue);
		
		/* View 정보를 반환하는 부분 */
		return "employeelist"; // "/WEB-INF/views/employeelist.jsp"
	}
	
	@RequestMapping(value = "/employeeinsertform", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeinsertform(ModelMap model) {

		// 검색 요청 데이터 수신 처리 -> 스프링이 자동 수신 (자료형 클래스 준비 or 멤버 변수 준비)
		
		List<Region> regionList = regionDAO.regionList();
		List<Department> departmentList = departmentDAO.departmentList();
		
		Map<String, String> map = null;
		List<Position> positionList = positionDAO.positionList(map);
		
		model.addAttribute("regionList", regionList);
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("positionList", positionList);
		
		return "employeeinsertform"; // "/WEB-INF/views/employeeinsertform.jsp"
	}
	
	@RequestMapping(value = "/employeeinsert", method = RequestMethod.POST)
	public String employeeinsert(Employee emp) {
		// 수정 요청 데이터 수신 처리 -> 스프링이 자동 수신 (자료형 클래스 준비 or 멤버 변수 준비)
		employeeDAO.employeeAdd(emp);
		
		/* View 정보를 반환하는 부분 */
		return "redirect:employeelist";
		
	}
	
	
	
}