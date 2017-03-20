package com.test.sts;

import java.util.List;

public interface RegionDAO {

	public List<Region> regionList();
	
	public int add(Region r);
	
	public int remove(Region r);
	
	public int modify(Region r);
}
