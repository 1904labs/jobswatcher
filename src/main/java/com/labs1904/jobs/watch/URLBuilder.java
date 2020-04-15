package com.labs1904.jobs.watch;

public class URLBuilder {

	private String url = null;
	private URLParamMap paramMap = null;
	
	public URLBuilder(String url) {
		this.url = url;
		paramMap = new URLParamMap();
	}
	
	public URLBuilder addParameter(String name, String value) {
		paramMap.put(name, value);
		return this;
	}
	
	public String toString() {
		return url + "?" + paramMap.toString();
	}
}
