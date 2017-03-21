package com.test.sts;

import java.util.List;

public interface PositionDAO {

	// 직위 정보 전체 출력
	public List<Position> positionList();
	
	// 직위 정보 추가
	public int add(Position p);
	
	// 직위 정보 삭제
	public int remove(Position p);
	
	// 직위 정보 수정
	public int modify(Position p);
	
	// 최소기본급 검색 쿼리 메소드
	public int getMinBasicPay(Position p);
	
}
