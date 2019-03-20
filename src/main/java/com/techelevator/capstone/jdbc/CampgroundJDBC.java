package com.techelevator.capstone.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.capstone.model.Campground;
import com.techelevator.capstone.model.CampgroundDAO;
import com.techelevator.capstone.model.Park;

public class CampgroundJDBC implements CampgroundDAO {

	private JdbcTemplate jdbcTemplate;

	public CampgroundJDBC(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Campground> listOfAllCampgrounds(Long parkId) {
		List<Campground> campgrounds = new ArrayList<Campground>();
		String campgroundNameSql = "SELECT * FROM campground WHERE park_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(campgroundNameSql, parkId);
		
		while (results.next()) {
			Campground c = mapRowToCampground(results);
			campgrounds.add(c);
		}
		return campgrounds;
	}
	
	private Campground mapRowToCampground(SqlRowSet results) {
		Campground campground = new Campground();
		campground.setCampgroundID(results.getLong("campground_id"));
		campground.setName(results.getString("name"));
		campground.setOpenFromMM(results.getInt("open_from_mm"));
		campground.setOpenToMM(results.getInt("open_to_mm"));
		campground.setDailyCost(results.getDouble("daily_fee"));
		campground.setParkID(results.getLong("park_id"));
		return campground;
	}

}
