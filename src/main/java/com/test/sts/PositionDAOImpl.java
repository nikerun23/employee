package com.test.sts;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//DAO 객체에는 @Repository 어노테이션 지정
@Repository
public class PositionDAOImpl implements PositionDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Position> positionList() {
		PositionDAO dao = sqlSession.getMapper(PositionDAO.class);
		List<Position> list = dao.positionList();
		return list;
	}

	@Override
	public int add(Position p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Position p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinBasicPay(Position p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modify(Position p) {
		// TODO Auto-generated method stub
		return 0;
	}

}
