package com.test.sts;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {

	// 직원 명단  전체 및 검색 출력용 메소드
	public List<Employee> employeeList(Map<String, String> map);
	
	// 직원 전체 인원수 출력 메소드
	public int totalCount();
	
	// 직원 정보 추가
	public int employeeAdd(Employee emp);
	
	// 직원 정보 수정
	public int employeeModify(Employee emp);
	
	// 직원 사진 추가
	public int pictureAdd(Employee emp);
	
	// 직원 정보 삭제
	public int employeRemove(Employee emp);
	
	// 직원 사진 수정
	public int pictureModify(Employee emp);
	
	// 사진 정보 검색 출력용
	public List<Employee> pictureList(Employee emp);
	
}
