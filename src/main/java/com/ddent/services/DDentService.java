package com.ddent.services;


import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.expression.ParseException;
//import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.ddent.entities.CodeSystem;
import com.ddent.entities.ConceptMap;
import com.ddent.entities.ValueSet;
import com.ddent.entities.study1.study;
import com.ddent.entities.study1.studyInFhir;
import com.ddent.entities.study1.studyPlusNlp;


@Component
@Service
public class DDentService {
	
	/**
	 * 
	 * Methods to help the controller
	 */
	
	XmlToPojoService xps= new XmlToPojoService();
	ClampExtraction ce=new ClampExtraction();
	studyPlusNlp spn= new studyPlusNlp();
	studyInFhir sif= new studyInFhir();
	
	
	public List<study> StudyVariables() throws ParserConfigurationException, SAXException, IOException{

		return xps.ListOfvariables();
	}
	
	
	
	public List<studyPlusNlp> Clampinfo() throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		return ce.StudyandNlpInfo();
	}
	
	
	
	
	public CodeSystem codeSystem() throws ParserConfigurationException, SAXException, IOException {
		
		return ce.FhirCodeSys();
		
	}
	
	
	public ValueSet FhirvalueSet() throws ParserConfigurationException, SAXException, IOException {
		return ce.FhirValueSet();
		
	}
	
	
	public ConceptMap fhirConceptMap() throws ParserConfigurationException, SAXException, IOException, ParseException {
		return ce.FhirConceptMap();
		
	}
	
	public CodeSystem CuiCodeSys() throws ParserConfigurationException, SAXException, IOException, ParseException {
		return ce.dbgapCuiCodeSys();
		
	}
	public ValueSet CuivalueSet() throws ParserConfigurationException, SAXException, IOException, ParseException {
		return ce.dbgapCuiValueSet();
		
	}

}
