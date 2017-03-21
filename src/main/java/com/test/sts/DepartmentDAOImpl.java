package com.test.sts;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//DAO 객체에는 @Repository 어노테이션 지정
@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

	/* MyBatis 설정 추가 */
	/* SqlSession 객체에 대한 자동 의존주입 */
	@Autowired
	private SqlSession sqlSession;
	
	// 부서 정보 전체 출력
	@Override
	public List<Department> departmentList() {
		DepartmentDAO dao = sqlSession.getMapper(DepartmentDAO.class);
		List<Department> list = dao.departmentList();
		return list;
	}

	// 부서 정보 추가
	@Override
	public int add(Department d) {
		int result = 0;
		DepartmentDAO dao = sqlSession.getMapper(DepartmentDAO.class);
		result = dao.add(d);
		return result;
	}

	// 부서 정보 삭제
	@Override
	public int remove(Department d) {
		int result = 0;
		DepartmentDAO dao = sqlSession.getMapper(DepartmentDAO.class);
		result = dao.remove(d);
		return result;
	}

	// 부서 정보 수정
	@Override
	public int modify(Department d) {
		int result = 0;
		DepartmentDAO dao = sqlSession.getMapper(DepartmentDAO.class);
		result = dao.modify(d);
		return result;
	}

}
