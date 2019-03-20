package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.techelevator.capstone.booking.BookingAdmin;
import com.techelevator.capstone.model.Campground;
import com.techelevator.capstone.model.Park;
import com.techelevator.capstone.model.Site;

public class Menu {

	private PrintWriter out;
	private Scanner in;
	private BookingAdmin bookingAdmin = new BookingAdmin();
	private List<Park> listOfParks = bookingAdmin.displayListOfParks();
	private Long selectedPark;
	private List<Campground> listOfCampgrounds;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
	
	/*
	 * VIEW PARK INTERFACE
	 */
	
	public Object getChoiceFromParkOptions() {
		Object choice = null;
		while(choice == null) {
			displayParkMenuOptions(listOfParks);
			choice = getParkChoiceFromUserInput();
		}
		return choice;
	}

	private Object getParkChoiceFromUserInput() {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			selectedPark = (long) Integer.parseInt(userInput);
			if (userInput == "Q") {
				System.exit(0);
			}
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= listOfParks.size()) {
				choice = selectedOption;
			}
		} catch(NumberFormatException e) {
			//eat exception
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
//		userSelectedParkId = bookingAdmin.mapOfSelectedPark().get(selectedPark).getParkId();
		return choice;
	}

	public void displayParkMenuOptions(List<Park> listOfParks) {
		out.println("\nSelect a Park for Further Details");
		int numberOptionForPark = 0;
		for(Park listNames : listOfParks) {
			numberOptionForPark++;
			out.println(numberOptionForPark+") " + listNames.getName());
		}
		out.println("Q) quit");
		out.flush();
	}
	
	/*
	 * DISPLAY SELECTED PARK INFORMATION SCREEN
	 */
	
	public void displaySelectedParkInformation() {
		out.println();
		out.println(bookingAdmin.mapOfSelectedPark().get(selectedPark).getName() + " National Park");	
		out.println("Location:        " + bookingAdmin.mapOfSelectedPark().get(selectedPark).getLocation());
		out.println("Established:     " + bookingAdmin.mapOfSelectedPark().get(selectedPark).getEstablishDate().toLocalDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		out.println("Area:            " + formatter.format(bookingAdmin.mapOfSelectedPark().get(selectedPark).getArea()) + " sq km");
		out.println("Annual Visitors: " + formatter.format(bookingAdmin.mapOfSelectedPark().get(selectedPark).getVisitors()));
		out.println();
		out.println(descriptionLineBreaks(bookingAdmin.mapOfSelectedPark().get(selectedPark).getDescription()));
	}
	
	public Object getSelectionFromParkMenu(Object[] options) {
		Object choice = null;
		while(choice == null) {
			displayParkMenuSelection(options);
			choice = getParkMenuOptionFromUserInput(options);
		}
		return choice;
	}
	
	private Object getParkMenuOptionFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption <= options.length) {
				choice = options[selectedOption-1];
			}
		} catch(NumberFormatException e) {
			//eat exception
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
			
		return choice;
	}
	
	public void displayParkMenuSelection(Object[] options) {
		out.println("\nSelect a Command");
		int index = 1;
		for(int i = 0; i < options.length; i++) {
			out.println(index +") " + options[i]);
			index++;
		}
		out.flush();
	}
	
	/*
	 * CAMPGROUND
	 */
	
	public void displayCampgroundListAndSelections(Object[] options) {
			listOfCampgrounds = bookingAdmin.displayListOfCampgrounds(selectedPark);
			displayCampgroundList(listOfCampgrounds);
	}
	
	public void displayCampgroundList(List<Campground> listOfCampgrounds) {
		out.println();
		out.println("Park Campgrounds");
		out.println(bookingAdmin.mapOfSelectedPark().get(selectedPark).getName() + " National Park Campgrounds");	
		out.println();
		out.println("     Name                              Open      Close       Daily Fee"); //TODO FORMATTING
		int numberOptionForCampground = 0;
		
			for(Campground listCampground : listOfCampgrounds) {
				numberOptionForCampground++;
				out.printf("%1s%-4d", "#", numberOptionForCampground);
				out.printf("%-34s", listCampground.getName());
				out.printf("%-10s", capitalCase(Month.of(listCampground.getOpenFromMM()).name()));
				out.printf("%-10s", capitalCase(Month.of(listCampground.getOpenToMM()).name()));
				out.printf("%-2s%-1s%-4.2f", " ", "$", listCampground.getDailyCost());
				out.println();
			}
		
		out.flush();
	}
	
	public Object getSelectionFromCampgroundMenu(Object[] options) {
		Object choice = null;
		while(choice == null) {
			displayCampgroundMenuSelection(options);
			choice = getCampgroundMenuOptionFromUserInput(options);
		}
		return choice;
	}
	
	private String getCampgroundMenuOptionFromUserInput(Object[] options) {
		String choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption <= options.length) {
				choice = (String) options[selectedOption-1];
			}
		} catch(NumberFormatException e) {
			//eat exception
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
			
		return choice;
	}
	
	public void displayCampgroundMenuSelection(Object[] options) {
		out.println("\nSelect a Command");
		int index = 1;
		for(int i = 0; i < options.length; i++) {
			out.println(index +") " + options[i]);
			index++;
		}
		out.flush();
	}
	
	/* 
	 * SEARCH FOR RESERVATION
	 */
	public void searchForReservation() {
		out.println("THIS FEATURE IS NOT AVAILABLE AT THIS TIME\nPLEASE SELECT A CAMPGROUND TO MAKE RESERVATION");
	}
	
	/*
	 * SEARCH FOR AVAILABLE RESERVATION
	 */
	
	/*
	 * Campground Choice
	 */
	public Object getCampGroundChoice(Object[] reservationPrompts) {
		Object choice = null;
		while(choice == null) {
			listOfCampgrounds = bookingAdmin.displayListOfCampgrounds(selectedPark);
			searchForCampgroundReservation(listOfCampgrounds);
			displayCampgroundPrompt(reservationPrompts[0]);
			choice = getChoiceOfCampgroundFromUser(listOfCampgrounds);
		}
		return choice;
	}
	
	private void searchForCampgroundReservation(List<Campground> listOfCampgrounds) {
		out.println();
		out.println("Search for Campground Reservation");	
		out.println("     Name                              Open      Close       Daily Fee");
		int numberOptionForCampground = 0;
		
			for(Campground listCampground : listOfCampgrounds) {
				numberOptionForCampground++;
				out.printf("%1s%-4d", "#", numberOptionForCampground);
				out.printf("%-34s", listCampground.getName());
				out.printf("%-10s", capitalCase(Month.of(listCampground.getOpenFromMM()).name()));
				out.printf("%-10s", capitalCase(Month.of(listCampground.getOpenToMM()).name()));
				out.printf("%-2s%-1s%-4.2f", " ", "$", listCampground.getDailyCost());
				out.println();
			}
		out.println();
		out.flush();
	}
	
	private Object getChoiceOfCampgroundFromUser(List<Campground> listOfCampgrounds) {
		Campground selectedCampground = null;
		Long selectedCampgroundParkId = null;
		String userInput = in.nextLine();
		try {
			int selectedChoice = Integer.valueOf(userInput);
			if (selectedChoice == 0) {
				return new Long(0);
			}
			if (selectedChoice > 0 && selectedChoice <= listOfCampgrounds.size()) {
				selectedCampground = listOfCampgrounds.get(selectedChoice -1);
				selectedCampgroundParkId = selectedCampground.getParkID();
			}
		} catch(Exception e) {
		//eat exception
		}
		if (selectedCampground == null) {
		out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return selectedCampgroundParkId;
	}
	
	/*
	 *  Arrival Date
	 */
	public Object getArrivalDateInfo(Object[] reservationPrompts) {
		Object choice = null;
		while(choice == null) {
			displayCampgroundPrompt(reservationPrompts[1]);
			choice = getArrivalDateFromUser();
		}
		return choice;
	}
	
	private Object getArrivalDateFromUser() {
		String selectedChoice = null;
		String userInput = in.nextLine();
		try {
			selectedChoice = userInput;
		} catch(Exception e) {
			//eat exception
		}
		if (selectedChoice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return selectedChoice;
	}
	
	/*
	 * Departure Date
	 */
	public Object getDepartureDateInfo(Object[] reservationPrompts) {
		Object choice = null;
		while(choice == null) {
			displayCampgroundPrompt(reservationPrompts[2]);
			choice = getDepartureDateFromUser();
		}
		return choice;
	}
	
	private Object getDepartureDateFromUser() {
		String selectedChoice = null;
		String userInput = in.nextLine();
		try {
			selectedChoice = (userInput);
		} catch(Exception e) {
			//eat exception
		}
		if (selectedChoice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return selectedChoice;
	}
	
	private void displayCampgroundPrompt(Object info) {
		out.print(info);
		out.flush();
	}
	
	/*
	 * PRINTING SITES THAT MATCH CRITERIA
	 */
	
	public void printingSiteResult(List<Site> results, int stayCount) {
		if (results.size() > 0) {
			out.println();
			out.println("Results Matching Your Search Criteria");
			out.println("Site No.    Max Occup.   Accessible?   Max RV Length   Utility   Cost");
			for(Site thisSite : results) {
				out.printf("%-12d", thisSite.getSiteNumber());
				out.printf("%-13d", thisSite.getMaxOccupancy());
				out.printf("%-14s", changeBooleanToString(thisSite.isAccessible()));
				out.printf("%-16s", changeRVBooleanToString(thisSite.getMaxRvLength()));
				out.printf("%-10s", changeUtilityBooleanToString(thisSite.isHasUtilities()));
				out.printf("%-1s%-4.2f", "$", (thisSite.getCost() * stayCount));
				out.println();
			}
		out.println();
		}
			else {
				out.println ("No Matching Sites Available");
			}	
	}
	
	/*
	 * GETTING RESERVATION INFO
	 */
	
	public Object getReservationSiteNo(Object[] reservationMenu) {
		Object choice = null;
		while(choice == null) {
			getSiteNoPrompt(reservationMenu[0]);
			choice = getReservationSiteFromUser();
		}
		return choice;
	}
	private int getReservationSiteFromUser() {
		int choice = 0;
		String userInput = in.nextLine();
		try {
			choice = Integer.valueOf(userInput);
			if (choice == 0) {
				return choice;
			}
		} catch(Exception e) {
			//eat exception
		}
		return choice;
	}
	
	private void getSiteNoPrompt(Object info) {
		out.print(info);
		out.flush();
	}
	
	/*
	 * GETTING RESERVATION NAME
	 */
	public Object getReservationName(Object[] reservationMenu) {
		Object choice = null;
		while(choice == null) {
			getNamePrompt(reservationMenu[1]);
			choice = getReservationNameFromUser();
		}
		return choice;
	}
	private Object getReservationNameFromUser() {
		String selectedChoice = null;
		String userInput = in.nextLine();
		try {
			selectedChoice = userInput;
		} catch(Exception e) {
			//eat exception
		}
		if (selectedChoice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return selectedChoice;
	}
	
	private void getNamePrompt(Object info) {
		out.print(info);
		out.flush();
	}
	
	public void printConfirmation(long confirmationId) {
		out.println();
		out.println("The reservation has been made and the confirmation id is " + confirmationId);
		out.flush();
	}
	
	
	/*
	 * ********************************************************************************************
	 * PRIVATE METHODS
	 * ********************************************************************************************
	 */

	private String capitalCase(String month) {
		String capitalMonth = month.toLowerCase();
		String firstLetter = capitalMonth.substring(0,1).toUpperCase();
		capitalMonth = capitalMonth.substring(1);
		String capitalCase = firstLetter + capitalMonth;
		return capitalCase;
	}
	
	private String descriptionLineBreaks(String description) {
		StringBuilder sb = new StringBuilder(description);
		int i = 0;
		while ((i = sb.indexOf(" ", i + 80)) != -1) {
		    sb.replace(i, i + 1, "\n");
		}
		return sb.toString();
	}
	
	private String changeBooleanToString(boolean answer) {
		if (answer) {
		return "Yes";
		}
	return "No";
	}
	
	private String changeUtilityBooleanToString(boolean answer) {
		if (answer) {
		return "Yes";
		}
	return "N/A";
	}
	
	private String changeRVBooleanToString(Long answer) {
		if (answer == 0) {
		return "N/A";
		}
	return bookingAdmin.displayListOfSites(selectedPark).get(0).getMaxRvLength().toString();
	}
}
