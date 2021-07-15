package com.ddent.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ddent.entities.study1.nlp;



@Component
@Service
public class PojoPlusNlpServices {
	
	HttpHeaders header = new HttpHeaders();
	RestTemplate restTemplate = new RestTemplate();
	Scanner sc = null;
	
	//private String url = "http://localhost:8080/getTable?text=";
	private String url2 = "http://localhost:8080/getJson?text=";
	
	
	/**
	 * 
	 * This methods extracs text from the NLP json and forms a NLP (POJO)
	 * 
	 * @param text
	 * @return list  of NLP
	 * @throws IOException
	 * @throws ParseException
	 */
	
	public List <nlp> getInfFromNlp(String text) throws IOException, ParseException {
		
		// Fields
		List <nlp> results=new ArrayList<nlp>();
		// Default reponse if the  response returns null 
		String response="{\"Results\":[{\"Location_Start\":0,\"Location_End\":0,\"Semantics\":\"null\",\"CUI\":\"null\",\"Assertion\":\"null\",\"Entity\":\"null\",\"Concept_Prob\":0.0}]}";

		
		// Use the Description to get nlp's
		header.add("Content-Type", "application/json");
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(header);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url2+text, HttpMethod.GET, requestEntity, String.class);
		
		if (responseEntity.hasBody()) {
			 response=responseEntity.getBody();
			
		}else {
		}
	    
	   // String nullresponse="null|null|null|null|null|null";
	    String nullresponse2="{\"Results\":[{\"Location_Start\":0,\"Location_End\":0,\"Semantics\":\"null\",\"CUI\":\"null\",\"Assertion\":\"null\",\"Entity\":\"null\",\"Concept_Prob\":0.0}]}";
	    
	    String finalresponse;
	    if(response.length() <= 14 ) {
	    //if(response != null) {
	    	finalresponse=nullresponse2;
	    }else {
	    	finalresponse=response;	
	    }
	    
	    // convert results to POJO
	    Scanner sc = null;
	    try {
	      sc = new Scanner( finalresponse);
	      
	      // Check if there is another line of input
	      while(sc.hasNextLine()){
	        String str = sc.nextLine();
	        
	        // parse each line using delimiter
	        parseDataFromJson(str);
	        if(parseDataFromJson(str)!=null) {
	        	results.addAll(parseDataFromJson(str));
	        }else {
	        	results.add(null);
	        }}
	    }finally{
	      if(sc != null)
	        sc.close();
	    }
		return results;
		
	  }    



	/**
	 * 
	 * This MEthods Extracts information from  the NLP
	 * 
	 * @param str
	 * @return a list of NLP
	 */
	public List <nlp> parseData(String str){	
	
		nlp clampinfo= new nlp();
		List <nlp> results=new ArrayList<nlp>();
		
		String locatioStart, locationEnd, semantics, cui,assertion,entity;
		Scanner lineScanner = new Scanner(str);
		lineScanner.useDelimiter("\\|");
		while(lineScanner.hasNext())
     {
			locatioStart = lineScanner.next();
			locationEnd = lineScanner.next();
			semantics = lineScanner.next();
			cui  = lineScanner.next();
			assertion = lineScanner.next();
			entity = lineScanner.next();
     
			clampinfo.setLocation_start(locatioStart);
			clampinfo.setLocation_end(locationEnd);
			clampinfo.setSemantics(semantics);
			clampinfo.setCui(cui);
			clampinfo.setAssertiion(assertion);
			clampinfo.setEntity(entity);
     
			results.add(clampinfo);

     }
			lineScanner.close();
			
			return results;
 
	
}
	/**
	 * Extracting info from the xml document in  jason  format
	 * 
	 * @param str
	 * @return list of nlp
	 * @throws ParseException
	 */
	public List <nlp> parseDataFromJson(String str) throws ParseException{
		
		
		JSONObject obj = new JSONObject(str);
		
		List <nlp> results=new ArrayList<nlp>();
		
		 // getting content of  result arrays
        JSONArray ja = obj.getJSONArray("Results");
        
        for (int i = 0; i < ja.length(); i++)
        {
        	nlp clampinfo= new nlp();
    		clampinfo.setLocation_start(Integer.toString(ja.getJSONObject(i).getInt("Location_Start")));
    		clampinfo.setLocation_end(Integer.toString(ja.getJSONObject(i).getInt("Location_End")));
    		clampinfo.setSemantics(ja.getJSONObject(i).getString("Semantics"));
    		
    		if(ja.getJSONObject(i).get("CUI").equals(null)) {
    			clampinfo.setCui("null");
    			//clampinfo.setConcept_prob("0.0");
    		}else {
    			clampinfo.setCui((String) ja.getJSONObject(i).get("CUI"));
    			
    		}
    		if(ja.getJSONObject(i).get("Assertion").equals(null)){
    			clampinfo.setAssertiion("null");
    		}else {
    			clampinfo.setAssertiion((String)ja.getJSONObject(i).get("Assertion"));
    			clampinfo.setConcept_prob(Double.toString(ja.getJSONObject(i).getDouble("Concept_Prob")));
    		}
    		clampinfo.setEntity((String) ja.getJSONObject(i).get("Entity"));
    		
    		results.add(clampinfo);
    		
    		
    		
        }
		return results;
	
	}
	
	
	
}