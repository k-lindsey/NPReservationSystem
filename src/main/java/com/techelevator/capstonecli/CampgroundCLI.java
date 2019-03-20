package com.techelevator.capstonecli;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.techelevator.capstone.booking.BookingAdmin;
import com.techelevator.capstone.model.Campground;
import com.techelevator.capstone.model.Site;
import com.techelevator.view.Menu;


public class CampgroundCLI {
		
	private static Menu menu;
	private static BookingAdmin bookingAdmin;
	private DateTimeFormatter dTFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	private List<Site> matchingSite;
	private int daysBetween;
	
	private static final String PARK_MENU_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String PARK_MENU_SEARCH_RESERVATION = "Search for Reservation";
	private static final String PARK_MENU_RETURN = "Return to Previous Screen";
	private static final String[] PARK_SELECTION_MENU = new String[] { PARK_MENU_VIEW_CAMPGROUNDS, 
																				PARK_MENU_SEARCH_RESERVATION,
																				PARK_MENU_RETURN};
	
	private static final String CAMPGROUND_MENU_AVAILABLE_RESERVATION = "Search for Available Reservation";
	private static final String CAMPGROUND_MENU_RETURN = "Return to Previous Screen";
	private static final String[] CAMPGROUND_SELECTION_MENU = new String[] { CAMPGROUND_MENU_AVAILABLE_RESERVATION,
																				CAMPGROUND_MENU_RETURN};
	
	private static final String WHICH_CAMPGROUND = "Which campground (enter 0 to cancel)? ";
	private static final String ARRIVAL_DATE = "What is the arrival date (mm/dd/yyyy)? ";
	private static final String DEPARTURE_DATE = "What is the departure date (mm/dd/yyyy)? ";
	private static final String [] RESERVATION_PROMPTS = new String[] { WHICH_CAMPGROUND,
																			ARRIVAL_DATE,
																			DEPARTURE_DATE};
	
	private static final String SITE_NO_TO_RESERVE = "Which site should be reserved (enter 0 to cancel)? ";
	private static final String RESERVATION_NAME = "What name should the reservation be made under? ";
	private static final String [] RESERVATION_MENU = new String[] { SITE_NO_TO_RESERVE,
																		RESERVATION_NAME};
	
	public CampgroundCLI(Menu menu) {
		this.menu = menu;
		this.bookingAdmin = new BookingAdmin();
	}
		
	public void run() {
		while(true) {
			menu.getChoiceFromParkOptions();
			menu.displaySelectedParkInformation();
			while(true) {
				String parkChoice = (String)menu.getSelectionFromParkMenu(PARK_SELECTION_MENU);
				
				if(parkChoice.equals(PARK_MENU_VIEW_CAMPGROUNDS)) {

					menu.displayCampgroundListAndSelections(CAMPGROUND_SELECTION_MENU);

					while(true) {
						
						String campgroundChoice = (String) menu.getSelectionFromCampgroundMenu(CAMPGROUND_SELECTION_MENU);
						
						if (campgroundChoice.equals(CAMPGROUND_MENU_AVAILABLE_RESERVATION)) {
							String campInfo = menu.getCampGroundChoice(RESERVATION_PROMPTS).toString();
							if (campInfo.equals("0")) {
								menu.getChoiceFromParkOptions();
								menu.displaySelectedParkInformation();
							}
							Long campId = Long.parseLong(campInfo);
							String arrivalDateInfo = (String) menu.getArrivalDateInfo(RESERVATION_PROMPTS);
							String departureDateInfo = (String) menu.getDepartureDateInfo(RESERVATION_PROMPTS);
							LocalDate arrivalDate = LocalDate.parse(arrivalDateInfo, dTFormat);
							LocalDate departureDate = LocalDate.parse(departureDateInfo, dTFormat);
							daysBetween = arrivalDate.until(departureDate).getDays();
//							matchingSite = bookingAdmin.searchingForReservation(campId, arrivalDateInfo, departureDateInfo);
							
							if (matchingSite.size() > 0) {
								menu.printingSiteResult(matchingSite, daysBetween);
								String selectedSiteNumber = menu.getReservationSiteNo(RESERVATION_MENU).toString();
								long selectedSiteNum = Long.parseLong(selectedSiteNumber);
								if (selectedSiteNum == 0) {
									menu.getChoiceFromParkOptions();
									menu.displaySelectedParkInformation();
								}
								String reservationName = (String) menu.getReservationName(RESERVATION_MENU);
								Long confirmationNo = bookingAdmin.createReservation(selectedSiteNum, reservationName, 
																arrivalDate, departureDate);		
								menu.printConfirmation(confirmationNo);
							}
						}
						
						if (campgroundChoice.equals(CAMPGROUND_MENU_RETURN)) {
							menu.getChoiceFromParkOptions();
							menu.displaySelectedParkInformation();
						}
						}
						break;
					}
				if(parkChoice.equals(PARK_MENU_SEARCH_RESERVATION)) {
					menu.searchForReservation();
				}
				if(parkChoice.equals(PARK_MENU_RETURN)) {
					menu.getChoiceFromParkOptions();
					menu.displaySelectedParkInformation();
				}
			}
			break;
		}
	}
	
	public static void main(String[] args) {
		menu = new Menu(System.in, System.out);
		CampgroundCLI application = new CampgroundCLI(menu);
		application.run();
	}
}
