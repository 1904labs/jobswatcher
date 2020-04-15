package com.labs1904.jobs.watch.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
@XmlAccessorType (XmlAccessType.FIELD)
public class JobResponse {

    @XmlElement(name = "query")
	private String query = "";

    @XmlElement(name = "location")
    private String location = "";
    
    @XmlElement(name = "dupefilter")
	private String dupefilter = "";
    
    @XmlElement(name = "highlight")
	private String highlight = "";
    
    @XmlElement(name = "totalresults")
    private String totalResults = "";
    
    @XmlElement(name = "start")
	private String start = "";
    
    @XmlElement(name = "end")
	private String end = "";
    
    @XmlElement(name = "radius")
	private String radius = "";
    
    @XmlElement(name = "pageNumber")
	private String pageNumber = "";
    
    @XmlElement(name = "results")
	private Jobs results = null;
	
	public void setQuery(String query) {
		this.query = query;
	}
	public String getQuery() {
		return query;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation() {
		return location;
	}

	public void setDupefilter(String dupefilter) {
		this.dupefilter = dupefilter;
	}
	public String getDupefilter() {
		return dupefilter;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	public String getHighlight() {
		return highlight;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}
	public int getTotalResults() {
		return Integer.parseInt(totalResults);
	}

	public void setStart(String start) {
		this.start = start;
	}
	public int getStart() {
		return Integer.parseInt(start);
	}

	public void setEnd(String end) {
		this.end = end;
	}
	public int getEnd() {
		return Integer.parseInt(end);
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}
	public String getRadius() {
		return radius;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageNumber() {
		return Integer.parseInt(pageNumber);
	}

	public void setResults(Jobs results) {
		this.results = results;
	}
	public Jobs getResults() {
		return results;
	}
	
	public static class Jobs {
		@XmlElement(name = "result")
		private List<Job> joblist = null;
		
		public List<Job> getJobs() {
			if (joblist == null) {
				joblist = new ArrayList<Job>();
			}
			return joblist;
		}
	}
	
}
