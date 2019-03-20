package com.techelevator.capstone.model;

public class Site {
	
	private Long siteId;
	private Long campgroundId;
	private Long siteNumber;
	private Long maxOccupancy;
	private boolean isAccessible;
	private Long maxRvLength;
	private boolean hasUtilities;
	private double cost;

	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Long getSiteId() {
	    return siteId;
	}
	public void setSiteId(Long siteId) {
	    this.siteId = siteId;
	}
	public Long getCampgroundId() {
	    return campgroundId;
	}
	public void setCampgroundId(Long campgroundId) {
	    this.campgroundId = campgroundId;
	}
	public Long getSiteNumber() {
	    return siteNumber;
	}
	public void setSiteNumber(Long siteNumber) {
	    this.siteNumber = siteNumber;
	}
	public Long getMaxOccupancy() {
	    return maxOccupancy;
	}
	public void setMaxOccupancy(Long maxOccupancy) {
	    this.maxOccupancy = maxOccupancy;
	}
	public boolean isAccessible() {
	    return isAccessible;
	}
	public void setAccessible(boolean isAccessible) {
	    this.isAccessible = isAccessible;
	}
	public Long getMaxRvLength() {
	    return maxRvLength;
	}
	public void setMaxRvLength(Long maxRvLength) {
	    this.maxRvLength = maxRvLength;
	}
	public boolean isHasUtilities() {
	    return hasUtilities;
	}
	public void setHasUtilities(boolean hasUtilities) {
	    this.hasUtilities = hasUtilities;
	}


}
