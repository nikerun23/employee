package com.test.sts;

import java.util.List;

public interface RegionDAO {

	// 지역 정보 전체 출력
	public List<Region> regionList();
	
	// 지역 정보 추가
	public int add(Region r);
	
	// 지역 정보 삭제
	public int remove(Region r);
	
	// 지역 정보 수정
	public int modify(Region r);
}
