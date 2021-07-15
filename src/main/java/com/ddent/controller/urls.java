package com.ddent.controller;

import java.io.IOException;
import java.util.List;


import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.ddent.entities.CodeSystem;
import com.ddent.entities.ConceptMap;
import com.ddent.entities.ValueSet;
import com.ddent.entities.study1.study;
import com.ddent.entities.study1.studyPlusNlp;
import com.ddent.services.ClampExtraction;
import com.ddent.services.DDentService;
import com.ddent.services.GetXmlFromUrl;
import com.ddent.services.InjestService;

/**
 * 
 * This are all the avaiable end points
 * for example http://localhost:4040/api/modifiedvariables
 * 
 * swagger is also  incorporated in this  application
 * http://localhost:4040/swagger-ui.html
 *
 */

@Component
@Controller
@RestController
@RequestMapping("/api")
public class urls {
	
	@Autowired
	DDentService dh;
	
	@Autowired
	GetXmlFromUrl gxu;
	
	@Autowired
	ClampExtraction ce;
	
	@Autowired
	InjestService is;

	String Datadic;
	String VarRep;
	


	@ResponseBody
    @GetMapping("/Variables")
    List<study> allVariables() throws ParserConfigurationException, SAXException, IOException {
    	
      return dh.StudyVariables();
}
    
    
    @ResponseBody
    @GetMapping("/Modifiedvariables")
    List<studyPlusNlp> allModifiedVariables() throws ParserConfigurationException, SAXException, IOException {
    return dh.Clampinfo();
}
    
    
    @ResponseBody
    @GetMapping("/CodeSystem")
    CodeSystem CodeSysFhir() throws ParserConfigurationException, SAXException, IOException {
    	
    return dh.codeSystem();
}
    
    @ResponseBody
    @GetMapping("/ValueSet")
    ValueSet ValueSetFhir() throws ParserConfigurationException, SAXException, IOException {
    	
    return dh.FhirvalueSet();
}
    @ResponseBody
    @GetMapping("/ConceptMap")
    ConceptMap ConceptMapFhir() throws ParserConfigurationException, SAXException, IOException, ParseException {
    	
    return dh.fhirConceptMap();
}
    
    @ResponseBody
    @GetMapping("/CuiCodeSystem")
    CodeSystem CuiCodeSysFhir() throws ParserConfigurationException, SAXException, IOException, ParseException {
    	
    return dh.CuiCodeSys();
}
    @ResponseBody
    @GetMapping("/CuiValueSet")
    ValueSet CuiValueSetFhir() throws ParserConfigurationException, SAXException, IOException, ParseException {
    	
    return dh.CuivalueSet();
}
    
    @ResponseBody
    @RequestMapping(value="/InjestIntoFhir",method = RequestMethod.POST)
    String InjestIntoFhir(
    		@RequestParam(required = false) String strDataDicUrl, 
    		@RequestParam(required = false) String strdocDataDicname,
    		@RequestParam(required = false) String strVarReportUrl,
    		@RequestParam(required = false) String strdocVarReportname) 
    				throws ParserConfigurationException, SAXException, IOException, ParseException {
    
    	this.Datadic=strdocDataDicname;
    	this.VarRep=strdocVarReportname;
    	
    	gxu.downloadDataDicXmlFile(strDataDicUrl, strdocDataDicname);
    	System.out.println("Data Dictionary Downloaded");
    	gxu.downloadVarReportXmlFile(strVarReportUrl, strdocVarReportname);
    	System.out.println("Var Report Downloaded");
    	
    	ce.docReader(strdocDataDicname);
    	ce.docReader2(strdocVarReportname);
    	
   	String resp=is.injest();
    	
    	System.out.println(" Injest into  Fhir complete");
    return "Injest complete /n"+resp;
}
    
    @ResponseBody
    @GetMapping("/DeleteInjestedFiles")
    boolean DeleteFilesInjestedToFhir() throws ParserConfigurationException, SAXException, IOException, ParseException {
    	
    	/*
       	 *This method delete the files after 33 min(2000000 millisec)
       	 * keep in mind that it takes a while to injest all the file
       	 */
    	
     	try {
    		System.out.println(" Injest into  Fhir complete!!!!");
    		System.out.println("============================================================================================");
    		System.out.println(" Will Delete "+this.Datadic +" & "+this.VarRep+" in  minutes");
    	Thread.sleep(2000000);
    	
    	is.deleteFile(this.Datadic);
       	is.deleteFile(this.VarRep);
       	System.out.println(" Deleted "+this.Datadic +" & "+this.VarRep);
       	System.out.println("============================================================================================");
    	
    } catch (InterruptedException e) {
    	e.printStackTrace();
    }
    	
    return true;
}

}

