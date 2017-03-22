package com.test.sts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* @Controller 어노테이션은 현재 클래스를 SpringWebMVC가 관리하는 컨트롤러로 등록할 때 사용 */
@Controller
public class DepartmentController {

	// @Autowired 어노테이션을 이용한 자동 의존 주입
	// DAO 객체에 대한 의존 주입
	@Autowired
	private DepartmentDAO departmentDAO;
	
	/* @RequestMapping 어노테이션에서 method 속성은 요청 방식(GET or POST)을 분석할 때 사용 */
	/* @RequestMapping 어노테이션과 연결된 메소드만 서블릿 요청에 대한 응답 메소드가 될 수 있다. */
	/* 응답 메소드의 매개 변수 지정은 SpringWebMVC에 의해 자동 분석되기 때문에, 필요한 객체를 요청할 때 사용한다. */
	/*
	 * method = {RequestMethod.GET, RequestMethod.POST}는 GET, POST 방식 두 가지 모두 수신 가능
	 */
	// 지역 정보 전체 출력
	@RequestMapping(value = "/departmentlist.it", method = { RequestMethod.GET, RequestMethod.POST })
	public String departmentlist(ModelMap model) {
		
		List<Department> list = null;
		list = departmentDAO.departmentList();
		
		/* 서블릿 액션의 결과를 JSP 페이지(View)에 전달하는 경우 Model 객체를 사용한다. */
		model.addAttribute("list",list);
		
		/* View 정보를 반환하는 부분 */
		return "departmentlist"; // "/WEB-INF/views/departmentlist.jsp"
	}
	
	// 지역 정보 추가
	@RequestMapping(value = "/departmentinsert.it", method = RequestMethod.POST)
	public String departmentinsert(Department d) {
		departmentDAO.add(d);
		/* View 정보를 반환하는 부분 */
		return "redirect:departmentlist.it";
	}
	
	// 지역 정보 삭제
	@RequestMapping(value = "/departmentdelete.it", method = RequestMethod.POST)
	public String departmentdelete(Department d) {
		departmentDAO.remove(d);
		/* View 정보를 반환하는 부분 */
		return "redirect:departmentlist.it";
	}
	
	// 지역 정보 수정
	@RequestMapping(value = "/departmentupdate.it", method = RequestMethod.POST)
	public String departmentupdate(Department d) {
		departmentDAO.modify(d);
		/* View 정보를 반환하는 부분 */
		return "redirect:departmentlist.it";
	}
	
	
}
