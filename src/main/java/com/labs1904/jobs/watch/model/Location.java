package com.labs1904.jobs.watch.model;

public class Location {

	private String lat = "";
	private String lon = "";
	
	public Location(String lon, String lat) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return this.lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	
}
