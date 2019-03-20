package com.techelevator;

import java.util.Date;
import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.capstone.jdbc.CampgroundJDBC;
import com.techelevator.capstone.jdbc.ParkJDBC;
import com.techelevator.capstone.model.Campground;

public class CampgroundJDBCIntegrationTest extends DAOIntegrationTest {

	private CampgroundJDBC dao;
	private ParkJDBC dao2;
	private JdbcTemplate jdbcTemplate;
	private long campgroundId;
	private long thisParkId;
	

	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());

		String insertParkSql = "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) " + 
				"VALUES (DEFAULT, 'Kool Park', 'Disney World', '1870-01-01', 4323, 283482, 'Kool Park') RETURNING park_id";
		dao2 = new ParkJDBC(getDataSource());
		thisParkId = jdbcTemplate.queryForObject(insertParkSql, Integer.class);
		
		String insertCampgroundSql = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) "+
											"VALUES (DEFAULT, 1, 'Flower', 03, 08, 30) RETURNING campground_id";
		dao = new CampgroundJDBC(getDataSource());
		campgroundId = jdbcTemplate.queryForObject(insertCampgroundSql, Integer.class);
	}
	
	@Test
	public void get_all_campgrounds_from_campground_table() {
		Long parkId = new Long (1);
		List<Campground> listOfCampgrounds = dao.listOfAllCampgrounds(parkId);
		Assert.assertEquals(4, listOfCampgrounds.size());
		
	}
	
}
