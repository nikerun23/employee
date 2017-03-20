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
	
	@Override
	public List<Department> departmentList() {
		DepartmentDAO dao = sqlSession.getMapper(DepartmentDAO.class);
		List<Department> list = dao.departmentList();
		return list;
	}

	@Override
	public int add(Department d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Department d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modify(Department d) {
		// TODO Auto-generated method stub
		return 0;
	}

}
