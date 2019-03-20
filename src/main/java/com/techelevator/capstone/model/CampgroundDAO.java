package com.techelevator.capstone.model;

import java.util.List;

public interface CampgroundDAO {
	
	public List<Campground> listOfAllCampgrounds(Long parkId);

}
