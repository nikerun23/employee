package com.test.sts;

import java.util.List;

public interface DepartmentDAO {

	// 부서 정보 전체 출력
	public List<Department> departmentList();
	
	// 부서 정보 추가
	public int add(Department d);
	
	// 부서 정보 삭제
	public int remove(Department d);

	// 부서 정보 수정
	public int modify(Department d);

}
