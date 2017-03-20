package com.test.sts;

import java.util.List;
import java.util.Map;

public interface PositionDAO {

	public List<Position> positionList();
	
	public int add(Position p);
	
	public int remove(Position p);
	
	//최소기본급 검색 쿼리 메소드
	public int getMinBasicPay(Position p);
	
	public int modify(Position p);
		
}
