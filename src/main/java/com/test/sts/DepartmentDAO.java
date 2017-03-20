package com.test.sts;

import java.util.List;

public interface DepartmentDAO {

	public List<Department> departmentList();
	
	public int add(Department d);
	
	public int remove(Department d);

	public int modify(Department d);

}
