package com.dharmonization;


import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import org.springframework.expression.ParseException;
//import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

import com.ddent.services.GetXmlFromUrl;
import com.ddent.services.PojoPlusNlpServices;

@SpringBootTest
class DDentServiceApplicationTests {

	
	
//	//works
//	@Test
//	void xmlToPojoTest() throws ParserConfigurationException, SAXException, IOException, JAXBException {
////		XmlToPojoService no = new XmlToPojoService();
////		no.ListOfvariables();
//		
//		XmlToPOJO nu= new XmlToPOJO();
//		nu.xmlFiiletoPojo().getListOfStudyPlusNlp();
//		
//	}
	
//	
//	//works
//	@Test
//	void clampTest() throws ParserConfigurationException, SAXException, IOException {
//		
//		ClampExtraction  no2= new ClampExtraction ();
//		no2.StudyandNlpInfo();
//	}
//	
	// works
//	@Test
//	void clampToPojoTest() throws ParserConfigurationException, SAXException, IOException, ParseException {
//
//		String text="broken leg";
//		PojoPlusNlpServices no = new PojoPlusNlpServices();
//		
//		no.parseDataFromJson(text);
//		//no.parseDataFromJson(text);
//	}
//	
	//works
//	@Test
//	void clampToPojoTestExtraction() throws ParserConfigurationException, SAXException, IOException , ParseException{
//
//		ClampExtraction ce= new ClampExtraction();
//		ce.StudyandNlpInfo();
//	}
//	
	//works
//		@Test
//		void FhirCodeSysTest() throws ParserConfigurationException, SAXException, IOException {
//
//			ClampExtraction ce= new ClampExtraction();
//			ce.FhirCodeSys();
//		}
//		//works
//		@Test
//		void FhirValueSetTest() throws ParserConfigurationException, SAXException, IOException {
//
//			ClampExtraction ce= new ClampExtraction();
//			ce.FhirValueSet();
//		}
//		//works
//		@Test
//		void FhirConceptMapTest() throws ParserConfigurationException, SAXException, IOException {
//
//			ClampExtraction ce= new ClampExtraction();
//			ce.FhirConceptMap();
//		}
   // works
	@Test
	void  downloadXml() throws IOException, ParserConfigurationException, SAXException {
		GetXmlFromUrl gxfu= new GetXmlFromUrl();
		//String p_sURL ="";
		//gxfu.getURLContent(p_sURL);
		gxfu.downloadDataDicXmlFile("Url place holder","New Study");
		//gxfu.getXml();
	}
}
