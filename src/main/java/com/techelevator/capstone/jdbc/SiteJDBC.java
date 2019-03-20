package com.techelevator.capstone.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.capstone.model.Site;
import com.techelevator.capstone.model.SiteDAO;

public class SiteJDBC implements SiteDAO {
	
	private JdbcTemplate jdbcTemplate;

	public SiteJDBC(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Site> getListOfAllSiteInfo(Long campgroundId) {
		List<Site> sites = new ArrayList<Site>();
		String siteSql = "SELECT * FROM site WHERE campground_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(siteSql, campgroundId);
		
		while (results.next()) {
			Site s = mapRowToSite(results);
			sites.add(s);
		}
		return sites;
	}
	
	@Override
	public List<Site> getListOfMatchingSites(Long campId, LocalDate arrDate, LocalDate depDate) {
		List<Site> matchingSites = new ArrayList<>();
		String theGrandSql = "Select distinct * from site " + 
				"join campground on site.campground_id = campground.campground_id " + 
				"where site.campground_id = ? " + 
				"and site_id not in " + 
				"(select site.site_id from site " + 
				"join reservation on reservation.site_id = site.site_id " + 
				"where (? > reservation.from_date AND ? < reservation.to_date) OR (? > reservation.from_date AND ? < reservation.to_date)) " + 
				"order by daily_fee " + 
				"LIMIT 5;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(theGrandSql, campId, arrDate, arrDate, depDate, depDate);
		
		while (results.next()) {
			Site s = mapRowToSite(results);
			matchingSites.add(s);
		}
		return matchingSites;
	}
	
	private Site mapRowToSite(SqlRowSet results) {
		Site site = new Site();
		site.setSiteId(results.getLong("site_id"));
		site.setSiteNumber(results.getLong("site_number"));
		site.setAccessible(results.getBoolean("accessible"));
		site.setHasUtilities(results.getBoolean("utilities"));
		site.setMaxOccupancy(results.getLong("max_occupancy"));
		site.setMaxRvLength(results.getLong("max_rv_length"));
		site.setCampgroundId(results.getLong("campground_id"));
		site.setCost(results.getDouble("daily_fee"));
		return site;
	}

	
}
