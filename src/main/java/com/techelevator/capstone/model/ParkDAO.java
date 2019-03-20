package com.techelevator.capstone.model;

import java.util.List;
import java.util.Map;

public interface ParkDAO {
	
	public List<Park> getAllParks();
	public Map<Long, Park> getAllParkInformation();
}
