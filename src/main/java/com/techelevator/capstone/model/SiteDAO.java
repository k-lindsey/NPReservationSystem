package com.techelevator.capstone.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {
	
	public List<Site> getListOfAllSiteInfo(Long campgroundID);
	public List<Site> getListOfMatchingSites(Long campId, LocalDate arrDate, LocalDate depDate);

}
