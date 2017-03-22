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
	
	// 직원 명단  전체 및 검색 출력용 메소드
	// key, value 값 전달을 위해서 Map 객체 이용
	@Override
	public List<Employee> employeeList(Map<String, String> map) {
		EmployeeDAO dao = sqlSession.getMapper(EmployeeDAO.class);
		List<Employee> list = dao.employeeList(map);
		return list;
	}

	// 직원 전체 인원수 출력 메소드
	@Override
	public int totalCount() {
		int result = 0;
		EmployeeDAO dao = sqlSession.getMapper(EmployeeDAO.class);
		result = dao.totalCount();
		return result;
	}

	//직원 정보 추가
	@Override
	public int employeeAdd(Employee emp) {
		int result = 0;
		EmployeeDAO dao = sqlSession.getMapper(EmployeeDAO.class);
		result = dao.employeeAdd(emp);
		return result;
	}

	// 직원 정보 수정
	@Override
	public int employeeModify(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 직원 정보 삭제
	// 데이터를 단독 전달하지 말고, Grade 클래스 또는 Map 클래스 이용할 것.
	@Override
	public int employeRemove(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 직원 사진 추가
	@Override
	public int pictureAdd(Employee emp) {
		int result = 0;
		EmployeeDAO dao = sqlSession.getMapper(EmployeeDAO.class);
		dao.pictureAdd(emp);
		return result;
	}

	// 직원 사진 수정
	@Override
	public int pictureModify(Employee emp) {
		int result = 0;
		EmployeeDAO dao = sqlSession.getMapper(EmployeeDAO.class);
		dao.pictureModify(emp);
		return result;
	}

	// 사진 정보 검색 출력용
	@Override
	public List<Employee> pictureList(Employee emp) {
		EmployeeDAO dao = sqlSession.getMapper(EmployeeDAO.class);
		List<Employee> list = dao.pictureList(emp);
		return list;
	}

}
