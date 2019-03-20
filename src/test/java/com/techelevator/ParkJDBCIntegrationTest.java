package com.techelevator;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.capstone.jdbc.ParkJDBC;
import com.techelevator.capstone.model.Park;

import org.junit.Assert;

public class ParkJDBCIntegrationTest extends DAOIntegrationTest {
	
	private ParkJDBC dao;
	private JdbcTemplate jdbcTemplate;
	private long parkId;
	
	@Before
	public void setup() {
		String insertParkSql = "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) " + 
				"VALUES (DEFAULT, 'Kool Park', 'Disney World', '1870-01-01', 4323, 283482, 'Kool Park') RETURNING park_id";
		jdbcTemplate = new JdbcTemplate(getDataSource());
		dao = new ParkJDBC(getDataSource());
		parkId = jdbcTemplate.queryForObject(insertParkSql, Integer.class);
	}
	
	@Test
	public void get_all_parks_in_park_table() {
		List<Park> allParks = dao.getAllParks();
		int originalSize = allParks.size();
		insertPark("Grand Canyon", "Ohio", new Date(2000-07-17), 8234, 984359, "Fun place");
		allParks = dao.getAllParks();
		Assert.assertEquals(originalSize + 1, allParks.size());	
	}
	
//	@Test
//	public void getting_all_information_from_a_selected_park() {
//		Map<Long, Park> parkMap = dao.returnSelectedParkInformation(parkId);
//		String name = parkMap.get(parkId).getName();
//		Assert.assertEquals("Kool Park", name);
//	}
	
	private int insertPark(String name, String location, Date establishDate, int area, int visitors, String description ) {
		String insertParkSql = "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING park_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(insertParkSql, name, location, establishDate, area, visitors, description);
		results.next();
		return results.getInt(1);
		
	}

}
