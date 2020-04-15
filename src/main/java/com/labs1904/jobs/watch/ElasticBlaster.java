package com.labs1904.jobs.watch;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ElasticBlaster {

	public void blast(ByteArrayOutputStream bos) {
		String charset = "UTF-8";
		URLConnection connection = null;
		try {
			connection = new URL("http://localhost:9200/jobs/job").openConnection();
			connection.setDoOutput(true); // Triggers POST.
		
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + "");
	
			try (OutputStream output = connection.getOutputStream()) {
			    output.write(bos.toByteArray());
			}
	
			InputStream response = connection.getInputStream();
			// ...
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
}
