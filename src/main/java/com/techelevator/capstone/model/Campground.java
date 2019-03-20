package com.techelevator.capstone.model;

public class Campground {
	
	private String name;
	private long campgroundID;
	private long parkID;
	private int openFromMM;
	private int openToMM;
	private double dailyCost;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
	public long getCampgroundID() {
		return campgroundID;
	}
	public void setCampgroundID(long campgroundID) {
		this.campgroundID = campgroundID;
	}
	public long getParkID() {
		return parkID;
	}
	public void setParkID(long parkID) {
		this.parkID = parkID;
	}
	public int getOpenFromMM() {
		return openFromMM;
	}
	public void setOpenFromMM(int openFromMM) {
		this.openFromMM = openFromMM;
	}
	public int getOpenToMM() {
		return openToMM;
	}
	public void setOpenToMM(int openToMM) {
		this.openToMM = openToMM;
	}
	public double getDailyCost() {
		return dailyCost;
	}
	public void setDailyCost(double dailyCost) {
		this.dailyCost = dailyCost;
	}
	

}
