package com.techelevator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.capstone.jdbc.ReservationJDBC;
import com.techelevator.capstone.model.Reservation;

public class ReservationJDBCIntegrationTest extends DAOIntegrationTest{
	
	private ReservationJDBC dao;
	private JdbcTemplate jdbcTemplate;
	private int siteId = 0;
	private int lastReservationId = 0;
	private DateTimeFormatter dTFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");


	@Before
	public void setup() {
	    jdbcTemplate = new JdbcTemplate(getDataSource());
	    
	    String insertSite = "insert into site (site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) "
	                            + "values (default, 1, 1000, 12, false, 35, false) RETURNING site_id";
	    siteId = jdbcTemplate.queryForObject(insertSite, Integer.class);
	    
	    String selectReservationSql = "select Max(reservation_id) from reservation";
	    lastReservationId = jdbcTemplate.queryForObject(selectReservationSql, Integer.class);
	}

	@Test
	public void creating_a_new_reservation_adds_one_to_count_of_database() {
	    Long newreservationId = dao.createNewReservation(siteId, "ABC", LocalDate.parse("03/10/2019", dTFormat), 
	                                                                    LocalDate.parse("03/17/2019", dTFormat));
	    Assert.assertEquals(Integer.valueOf(lastReservationId + 1), Long.valueOf(newreservationId));
	}
}