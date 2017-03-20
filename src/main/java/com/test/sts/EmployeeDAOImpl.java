package com.test.sts;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//DAO 객체에는 @Repository 어노테이션 지정
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	/* MyBatis 설정 추가 */
	/* SqlSession 객체에 대한 자동 의존주입 */
	@Autowired
	private SqlSession sqlSession;
	
	// key, value 값 전달을 위해서 Map 객체 이용
	@Override
	public List<Employee> employeeList(Map<String, String> map) {
		EmployeeDAO dao = sqlSession.getMapper(EmployeeDAO.class);
		List<Employee> list = dao.employeeList(map);
		return list;
	}

	@Override
	public int totalCount() {
		int result = 0;
		EmployeeDAO dao = sqlSession.getMapper(EmployeeDAO.class);
		result = dao.totalCount();
		return result;
	}

	@Override
	public int employeeAdd(Employee emp) {
		int result = 0;
		EmployeeDAO dao = sqlSession.getMapper(EmployeeDAO.class);
		result = dao.employeeAdd(emp);
		return result;
	}

	@Override
	public int employeeModify(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int pictureAdd(Employee emp) {
		int result = 0;
		EmployeeDAO dao = sqlSession.getMapper(EmployeeDAO.class);
		dao.employeeAdd(emp);
		return result;
	}

	// 데이터를 단독 전달하지 말고, Grade 클래스 또는 Map 클래스 이용할 것.
	@Override
	public int employeRemove(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int pictureModify(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Employee> pictureList(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String login(String id, String pw, String admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
