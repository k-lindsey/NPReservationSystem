package com.techelevator.capstone.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.capstone.model.Park;
import com.techelevator.capstone.model.ParkDAO;

public class ParkJDBC implements ParkDAO {
	
	private JdbcTemplate jdbcTemplate;

	public ParkJDBC(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<Park>();
		String parkNameSql = "SELECT park_id, name FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(parkNameSql);
		
		while (results.next()) {
			Park p = mapRowToPark(results);
			parks.add(p);
		}
		return parks;
	}
	
	public Map<Long, Park> getAllParkInformation() {
		Map<Long, Park> parkMap = new HashMap<Long, Park>();
		String parkMapSql = "SELECT * FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(parkMapSql);
		
		while (results.next()) {
			Park p = mapRowToParkMap(results);
			Long key = mapKeyToParkMap(results);
			parkMap.put(key, p);
		}
		return parkMap;
	}
	
	private Park mapRowToParkMap(SqlRowSet results) {
		Park park = new Park();
		park.setName(results.getString("name"));
		park.setLocation(results.getString("location"));
		park.setEstablishDate(results.getDate("establish_date"));
		park.setArea(results.getInt("area"));
		park.setVisitors(results.getInt("visitors"));
		park.setDescription(results.getString("description"));
		return park;
	}
	
	private Long mapKeyToParkMap(SqlRowSet results) {
		Park parkKey = new Park();
		parkKey.setParkId(results.getLong("park_id"));
		Long key = parkKey.getParkId();
		return key;
	}
	
	private Park mapRowToPark(SqlRowSet results) {
		Park park = new Park();
		park.setParkId(results.getLong("park_id"));
		park.setName(results.getString("name"));
		return park;
	}

}
