package com.techelevator.capstone.booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.capstone.jdbc.CampgroundJDBC;
import com.techelevator.capstone.jdbc.ParkJDBC;
import com.techelevator.capstone.jdbc.ReservationJDBC;
import com.techelevator.capstone.jdbc.SiteJDBC;
import com.techelevator.capstone.model.Campground;
import com.techelevator.capstone.model.CampgroundDAO;
import com.techelevator.capstone.model.Park;
import com.techelevator.capstone.model.ParkDAO;
import com.techelevator.capstone.model.ReservationDAO;
import com.techelevator.capstone.model.Site;
import com.techelevator.capstone.model.SiteDAO;

public class BookingAdmin {
	
	private ParkDAO parkDao;
	private CampgroundDAO campgroundDao;
	private ReservationDAO reservationDao;
	private SiteDAO siteDao;
	BasicDataSource dataSource = new BasicDataSource();
	private DateTimeFormatter dTFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	
	public BookingAdmin() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		parkDao = new ParkJDBC(dataSource);
		campgroundDao = new CampgroundJDBC(dataSource);
		reservationDao = new ReservationJDBC(dataSource);
		siteDao = new SiteJDBC(dataSource);
	}
	
	public List<Park> displayListOfParks() {
		List<Park> listOfAllParks = parkDao.getAllParks();
		return listOfAllParks;
	}
	
	public Map<Long, Park> mapOfSelectedPark() {
		Map<Long, Park> mapOfSelectedPark = parkDao.getAllParkInformation();
		return mapOfSelectedPark;
	}
	
	public List<Campground> displayListOfCampgrounds(Long parkId) {
		List<Campground> listOfAllCampgrounds = campgroundDao.listOfAllCampgrounds(parkId);
		return listOfAllCampgrounds;
	}
	
	public List<Site> searchingForReservation(Campground campInfo, String fromDate, String toDate){
		long selectedCampground = campInfo.getCampgroundID();
		LocalDate arrivalDate = LocalDate.parse(fromDate, dTFormat);
		LocalDate departureDate = LocalDate.parse(toDate, dTFormat);	
		List<Site> matchingSites = siteDao.getListOfMatchingSites(selectedCampground, arrivalDate, departureDate);
		return matchingSites;
	}
	
	public List<Site> displayListOfSites(Long campgroundId){
		List<Site> listOfAllSites = siteDao.getListOfAllSiteInfo(campgroundId);
		return listOfAllSites;
	}
	
	public Long createReservation(Long siteId, String name, LocalDate arrDate, LocalDate depDate) {
		Long confirmationNo = reservationDao.createNewReservation(siteId, name, arrDate, depDate);
		return confirmationNo;
	}
	

}
