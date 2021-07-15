package com.dharmonization.services;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import com.dharmonization.entities.CodeSystem;
import com.dharmonization.entities.ConceptMap;
import com.dharmonization.entities.ValueSet;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Service
public class InjestService {
	
	@Autowired
	DDentService dh;
	
	
	HttpHeaders header = new HttpHeaders();
	
	RestTemplate restTemplate = new RestTemplate();
	Scanner sc = null;
	
	private String ConceptMapUrl = "http://localhost:8000/ConceptMap";
	private String ValueSetUrl = "http://localhost:8000/ValueSet";
	private String CodeSystemUrl = "http://localhost:8000/CodeSystem";
	
	
	/**
	 * 
	 * This Method takes the pojo object transforms it to a jason string and pass it as a body when making
	 * the injest post call
	 */
	public String injest() {
		
		header.add("Content-Type", "application/json");
		String ConceptMapResponse ="";
		String ValueSetResponse ="";
		String CodeSystemResponse ="";
		
		String completeResponse="";
		
		
		try {
			ValueSet vlueset;
			vlueset = dh.FhirvalueSet();
			ObjectMapper vluesetmapper = new ObjectMapper();
			String ValueSetBody= vluesetmapper.writeValueAsString(vlueset);
			
			System.out.println("VaueSet About to ingest");
			HttpEntity<String> requestEntity = new HttpEntity<String>(ValueSetBody,header);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ValueSetUrl, HttpMethod.POST, requestEntity, String.class);
			System.out.println("ValueSet ingested in Fhir Status Code :"+responseEntity.getStatusCodeValue());
			
			
			if (responseEntity.hasBody()) {
				ValueSetResponse=responseEntity.getBody();
				String res="*** ValueSet ****\n"+responseEntity.getBody()+"\n***********************";
				completeResponse.concat(res);
				
				System.out.println("Response from  Valueset  Ingestion");
				System.out.println("---------------------------------------------------------------------");
				System.out.println(ValueSetResponse);
				System.out.println("---------------------------------------------------------------------");
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
		
		try {
			ConceptMap cptmap;
			cptmap = dh.fhirConceptMap();
			ObjectMapper cptmapmapper = new ObjectMapper();
			String ConceptMapBody= cptmapmapper.writeValueAsString(cptmap);
			
			System.out.println("ConceptMap About to ingest");
			HttpEntity<String> requestEntity1 = new HttpEntity<String>(ConceptMapBody,header);
			ResponseEntity<String> responseEntity1 = restTemplate.exchange(ConceptMapUrl, HttpMethod.POST, requestEntity1, String.class);
			System.out.println("ConceptMap ingested in Fhir Status code :"+responseEntity1.getStatusCodeValue());
			
			if (responseEntity1.hasBody()) {
				ConceptMapResponse=responseEntity1.getBody();
				String res="*** ConceptMap **** \n"+responseEntity1.getBody()+"\n***********************";
				completeResponse.concat(res);
				System.out.println("Response from  ConceptMap  Ingestion");
				System.out.println("---------------------------------------------------------------------");
				System.out.println(ConceptMapResponse);
				System.out.println("---------------------------------------------------------------------");
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
		
		try {
			CodeSystem cdsys;
			cdsys = dh.codeSystem();
			ObjectMapper cdsysmapper = new ObjectMapper();
			String CodeSystemBody= cdsysmapper.writeValueAsString(cdsys);
			
			System.out.println("CodeSystem About to ingest");
			HttpEntity<String> requestEntity2 = new HttpEntity<String>(CodeSystemBody,header);
			ResponseEntity<String> responseEntity2 = restTemplate.exchange(CodeSystemUrl, HttpMethod.POST, requestEntity2, String.class);
			System.out.println("CodeSystem ingested in Fhir Status code :"+responseEntity2.getStatusCodeValue());
			
			if (responseEntity2.hasBody()) {
				CodeSystemResponse=responseEntity2.getBody();
				String res="*** CodeSystem **** \n"+responseEntity2.getBody()+"\n***********************";
				completeResponse.concat(res);
				System.out.println("Response from  CodeSystem  Ingestion");
				System.out.println("---------------------------------------------------------------------");
				System.out.println(CodeSystemResponse);
				System.out.println("---------------------------------------------------------------------");
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		
		
		System.out.println("INJEST ValueSet COMPLLETE !!!!!!!!!!!!!!!!!!!");
		System.out.println("INJEST ConceptMap COMPLLETE!!!!!!!!!!!!");
		System.out.println("INJEST CodeSystemCOMPLLETE!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		System.out.println("INJEST COMPLLETE!!!!!!!!!!!! YAY !!!!!!!!!!!!!!!!");
		
		
		return completeResponse;
		
		
		
	}
	
public String deleteFile(String fileToDelete) {
		
		String resp="No Response";
		 File file = new File(fileToDelete);
		 
		 boolean Filedeleted= file.delete();
		 
		    if(Filedeleted) 
	        { 
	            resp="File"+fileToDelete+"deleted successfully"; 
	        } 
	        else
	        { 
	        	resp="Failed to delete the file "+fileToDelete; 
	        } 
		 
		
		return resp;
		
	}

}