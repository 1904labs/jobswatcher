package com.labs1904.jobs.watch.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
@XmlAccessorType (XmlAccessType.FIELD)
public class Job {
	  @XmlElement(name = "jobtitle")
	  private String jobTitle;
	  
	  @XmlElement(name = "company")
	  private String company;
	  
	  @XmlElement(name = "city")
	  private String city;

	  @XmlElement(name = "country")
	  private String country;

	  @XmlElement(name = "formattedLocation")
	  private String formattedLocation;

	  @XmlElement(name = "source")
	  private String source;

	  @XmlElement(name = "date")
	  private String date;

	  @XmlElement(name = "snippet")
	  private String snippet;

	  @XmlElement(name = "url")
	  private String url;

	  @XmlElement(name = "onmousedown")
	  private String onmousedown;

	  @XmlElement(name = "latitude")
	  private String latitude;

	  @XmlElement(name = "longitude")
	  private String longitude;

	  @XmlElement(name = "jobkey")
	  private String jobkey;
	  
	  @XmlElement(name = "sponsored")
	  private String sponsored;

	  @XmlElement(name = "expired")
	  private String expired;

	  @XmlElement(name = "indeedApply")
	  private String indeedApply;

	  @XmlElement(name = "formattedLocationFull")
	  private String formattedLocationFull;

	  @XmlElement(name = "formattedRelativeTime")
	  private String formattedLocationRelative;

		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}
		public String getJobTitle() {
			return jobTitle;
		}

		public void setCompany(String company) {
			this.company = company;
		}
		public String getCompany() {
			return company;
		}

		public void setCity(String city) {
			this.city = city;
		}
		public String getCity() {
			return city;
		}

		public void setCountry(String country) {
			this.country = country;
		}
		public String getCountry() {
			return country;
		}

		public void setFormattedLocation(String formattedLocation) {
			this.formattedLocation = formattedLocation;
		}
		public String getFormattedLocation() {
			return formattedLocation;
		}

		public void setSource(String source) {
			this.source = source;
		}
		public String getSource() {
			return source;
		}

		public void setDate(String date) {
			this.date = date;
		}
		public String getDate() {
			return date;
		}

		public void setSnippet(String snippet) {
			this.snippet = snippet;
		}
		public String getSnippet() {
			return snippet;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		public String getUrl() {
			return url;
		}

		public void setOnmousedown(String onmousedown) {
			this.onmousedown = onmousedown;
		}
		public String getOnmousedown() {
			return onmousedown;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLatitude() {
			return latitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getLongitude() {
			return longitude;
		}
		
		public Location getLocation() {
			return new Location(this.longitude, this.latitude);
		}

		public void setJobkey(String jobkey) {
			this.jobkey = jobkey;
		}
		public String getJobkey() {
			return jobkey;
		}

		public void setSponsored(String sponsored) {
			this.sponsored = sponsored;
		}
		public String getSponsored() {
			return sponsored;
		}

		public void setExpired(String expired) {
			this.expired = expired;
		}
		public String getExpired() {
			return expired;
		}

		public void setIndeedApply(String indeedApply) {
			this.indeedApply = indeedApply;
		}
		public String getIndeedApply() {
			return indeedApply;
		}
		
		public void setFormattedLocationFull(String formattedLocationFull) {
			this.formattedLocationFull = formattedLocationFull;
		}
		public String getFormattedLocationFull() {
			return formattedLocationFull;
		}

		public void setFormattedLocationRelative(String formattedLocationRelative) {
			this.formattedLocationRelative = formattedLocationRelative;
		}
		public String getFormattedLocationRelative() {
			return formattedLocationRelative;
		}		

}
