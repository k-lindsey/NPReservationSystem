package com.techelevator.capstone.model;

import java.time.LocalDate;

public interface ReservationDAO {
	public Long createNewReservation(long siteId, String name, LocalDate fromDate, LocalDate toDate);
}
