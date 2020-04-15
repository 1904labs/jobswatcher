package com.labs1904.jobs.watch;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs1904.jobs.watch.model.Job;
import com.labs1904.jobs.watch.model.JobResponse;
import com.labs1904.jobs.watch.model.LeedRequest;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class App 
{
	private final int LIMIT = 25;
	private final ElasticBlaster blaster = new ElasticBlaster();
	
    public static void main( String[] args ) throws InterruptedException, IOException
    {
    	App app = new App();
		HashMap<String,String> configMap = new HashMap<>();
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("test");
		MongoCollection collection = db.getCollection("jobsconfig");
		for(MongoCursor it = collection.find().iterator(); it.hasNext();){
			Document doc = (Document)it.next();
			Set<Map.Entry<String,Object>> entrySet = doc.entrySet();
			for(Iterator<Map.Entry<String,Object>> it1  = entrySet.iterator(); it1.hasNext();){
				Map.Entry<String,Object> keyVal = it1.next();
				if(!keyVal.getKey().equals("_id")){
					configMap.put(keyVal.getKey(), (String)keyVal.getValue());
				}
			}
		}
    	LeedRequest request = new LeedRequest();
		Thread.sleep(1000);
    	System.out.println("");
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	bos.write("{ \"jobs\":[".getBytes());
    	app.getJobs(request, bos, configMap);
    	bos.write("]}".getBytes());
    	System.out.println(bos.toString());
    }
    
    public JobResponse getJobs(LeedRequest request, HashMap<String,String> configMap) throws InterruptedException {
    	JobResponse response = null;
    	URLBuilder urlbuilder = null;    	
    	URL url = null;
		Thread.sleep(1000);
		try {
    		// http://www.indeed.com/jobs?as_and=java+nosql+&as_phr=&as_any=&as_not=&as_ttl=&as_cmp=&jt=all&st=&salary=&radius=100&l=St.+Louis%2C+MO&fromage=any&limit=10&sort=&psf=advsrch
    		
			urlbuilder = new URLBuilder("http://api.indeed.com/ads/apisearch");
			for(Iterator<Map.Entry<String,String>> it =  configMap.entrySet().iterator() ; it.hasNext();){
				Map.Entry<String,String> entry = it.next();
				urlbuilder.addParameter(entry.getKey(),entry.getValue());
			}
			urlbuilder.addParameter("start", request.getStart() + "")
					.addParameter("limit", LIMIT + "");

			/*urlbuilder.addParameter("publisher", "6586127283101628")
			          .addParameter("v", "2")
					  .addParameter("format", "xml")
					  .addParameter("q", "html java")
					  .addParameter("l", "st. louis, mo")
					  .addParameter("sort", "relevance")
					  .addParameter("radius", "150")
					  .addParameter("st", "employer")  // jobsite, employer
					  .addParameter("jt", "fulltime") // fulltime, parttime, contract, internship, temporary
				      .addParameter("start", request.getStart() + "")
				      .addParameter("limit", LIMIT + "")
				      .addParameter("fromage", "10")
				      .addParameter("highlight", "0")
				      .addParameter("filter", "1")
				      .addParameter("latlong", "1")
				      .addParameter("co", "us")
				      .addParameter("chnl", "")
				      .addParameter("userip", "10.40.41.104")
				      .addParameter("useragent", "Chrome/xyz");
		    */
			url = new URL(urlbuilder.toString());
			URLConnection httpUrl = url.openConnection();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpUrl.getInputStream()));
			XMLMarshaller<JobResponse> parser = new XMLMarshaller<JobResponse>(JobResponse.class);

			response = parser.parse(reader);
			reader.close();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    	
    	return response;
    }
    
    // This method is not used in this application it was used behind a servlet which passed a writer
    // from the HttpServletRequest Object. 
		public void getJobs(LeedRequest request, Writer writer, HashMap<String,String> configMap) throws InterruptedException {
    	JobResponse jobResponse = getJobs(request,configMap);
		JsonFactory factory = new JsonFactory();
		JsonGenerator jsonGenerator;
		try {
			jsonGenerator = factory.createGenerator(writer);
			jsonGenerator.setCodec(new ObjectMapper());
			jsonGenerator.writeObject(jobResponse);    	
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    }
    
    public void getJobs(LeedRequest request, ByteArrayOutputStream byteArrayOS, HashMap<String,String> configMap) throws InterruptedException, IOException {
    	JobResponse jobResponse = getJobs(request, configMap);
    	int count = 0;

    	if (request.getStart() == jobResponse.getTotalResults()) {
    		return;
    	}
    	
		JsonFactory factory = new JsonFactory();
		JsonGenerator jsonGenerator;
		try {
			
			jsonGenerator = factory.createGenerator(byteArrayOS);
			jsonGenerator.setCodec(new ObjectMapper());
			for (Job j : jobResponse.getResults().getJobs()) {
				ByteArrayOutputStream requestStream = new ByteArrayOutputStream();
				JsonGenerator requestGenerator = factory.createGenerator(requestStream);

				requestGenerator.setCodec(new ObjectMapper());
				requestGenerator.writeObject(j);
				
				System.out.println(requestStream.toString());
				blaster.blast(requestStream);
				
				count++;

				jsonGenerator.writeObject(j);
				if (count < jobResponse.getResults().getJobs().size()) {
					byteArrayOS.write(",".getBytes());
				}
			}
			byteArrayOS.write("\n".getBytes());
		
		
			System.out.println(" Total Results: " + jobResponse.getTotalResults() + " getStart: " + jobResponse.getStart() + " getEnd: " + jobResponse.getEnd());

			LeedRequest newRequest = new LeedRequest(); 
			newRequest.setStart(jobResponse.getEnd());
			newRequest.setQuery(request.getQuery());
	        byteArrayOS.write(",".getBytes());			
			getJobs(newRequest, byteArrayOS, configMap);

			byteArrayOS.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    }
}
