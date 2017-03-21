package com.test.sts;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//DAO 객체에는 @Repository 어노테이션 지정
@Repository
public class PositionDAOImpl implements PositionDAO {

	@Autowired
	private SqlSession sqlSession;
	
	// 직위 정보 전체 출력
	@Override
	public List<Position> positionList() {
		PositionDAO dao = sqlSession.getMapper(PositionDAO.class);
		List<Position> list = dao.positionList();
		return list;
	}

	// 직위 정보 추가
	@Override
	public int add(Position p) {
		int result = 0;
		PositionDAO dao = sqlSession.getMapper(PositionDAO.class);
		dao.add(p);
		return result;
	}

	// 직위 정보 삭제
	@Override
	public int remove(Position p) {
		int result = 0;
		PositionDAO dao = sqlSession.getMapper(PositionDAO.class);
		dao.remove(p);
		return result;
	}

	// 직위 정보 수정
	@Override
	public int modify(Position p) {
		int result = 0;
		PositionDAO dao = sqlSession.getMapper(PositionDAO.class);
		dao.modify(p);
		return result;
	}
	
	// 최소기본급 검색
	@Override
	public int getMinBasicPay(Position p) {
		int result = 0;
		PositionDAO dao = sqlSession.getMapper(PositionDAO.class);
		result = dao.getMinBasicPay(p);
		return result;
	}


}
