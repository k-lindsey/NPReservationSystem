package com.techelevator.capstone.jdbc;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.capstone.model.Reservation;
import com.techelevator.capstone.model.ReservationDAO;

public class ReservationJDBC implements ReservationDAO {
	
	private JdbcTemplate jdbcTemplate;

	public ReservationJDBC(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	 
	@Override
	public Long createNewReservation(long siteId, String name, LocalDate fromDate, LocalDate toDate) {
		String insertReservationSql = "INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date) " + 
				"VALUES (DEFAULT, ?, ?, ?, ?, current_date) RETURNING reservation_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(insertReservationSql, siteId, name, fromDate, toDate);
		results.next();
		Long reservationId = results.getLong("reservation_id");
		return reservationId;
	}
	
	private Reservation mapRowToReservation(SqlRowSet results) {
		Reservation reservation = new Reservation();
		reservation.setName(results.getString("name"));
		reservation.setReservationId(results.getLong("reservation_id"));
		reservation.setSiteId(results.getLong("site_id"));
		reservation.setFromDate(results.getDate("from_date").toLocalDate());
		reservation.setToDate(results.getDate("to_date").toLocalDate());
		reservation.setCreateDate(results.getDate("create_date").toLocalDate());
		return reservation;
	}

	

}
