package com.labs1904.jobs.watch;

import static org.junit.Assert.*;

import org.junit.Test;

public class URLParamMapTest {

	@Test
	public void URLMapCreatesUrlMapString() {
		URLParamMap urlMap = new URLParamMap();
		urlMap.put("testParam1", "val1");
		urlMap.put("testParam2", "val2");
		
		String url = urlMap.toString();
	    assertEquals("testParam1=val1&testParam2=val2", url);
	}
	
	@Test
	public void URLMapCreatesURLEncodedWithASpace() {
		URLParamMap urlMap = new URLParamMap();
		urlMap.put("testParam1", "test space");
		urlMap.put("testParam2", "2.0");
		
		String url = urlMap.toString();
		assertEquals("testParam1=test+space&testParam2=2.0", url);
	}
}
