package com.labs1904.jobs.watch;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;

public class URLParamMap extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		StringBuilder paramStr = new StringBuilder();
		Set<String> keys = this.keySet();
		int counter = 0;
		for (String key : keys) {
			try {
				String value = this.get(key);
					paramStr.append(key)
			        .append("=")
			        .append(URLEncoder.encode(value, "UTF-8"));
					
				if (counter < this.size() - 1) {
			        paramStr.append("&");
				}
				counter++;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return paramStr.toString();
	}

}
