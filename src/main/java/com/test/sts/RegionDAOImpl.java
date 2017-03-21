package com.test.sts;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//DAO 객체에는 @Repository 어노테이션 지정
@Repository
public class RegionDAOImpl implements RegionDAO {

	/* MyBatis 설정 추가 */
	/* SqlSession 객체에 대한 자동 의존주입 */
	@Autowired
	private SqlSession sqlSession;
	
	// 지역 정보 전체 출력
	@Override
	public List<Region> regionList() {
		RegionDAO dao = sqlSession.getMapper(RegionDAO.class);
		List<Region> list = dao.regionList();
		return list;
	}

	// 지역 정보 추가
	@Override
	public int add(Region r) {
		int result = 0;
		RegionDAO dao = sqlSession.getMapper(RegionDAO.class);
		result = dao.add(r);
		return result;
	}

	// 지역 정보 삭제
	@Override
	public int remove(Region r) {
		int result = 0;
		RegionDAO dao = sqlSession.getMapper(RegionDAO.class);
		result = dao.remove(r);
		return result;
	}

	// 지역 정보 수정
	@Override
	public int modify(Region r) {
		int result = 0;
		RegionDAO dao = sqlSession.getMapper(RegionDAO.class);
		result = dao.modify(r);
		return result;
	}

}
