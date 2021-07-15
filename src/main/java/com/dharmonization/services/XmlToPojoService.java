package com.dharmonization.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import com.dharmonization.entities.study1.study;


@Component
@Service
public class XmlToPojoService {
	
	
	
	/**
	 * 
	 * This method is used to extract the list of variables from the xml
	 * 
	 * TODO:
	 * 1) Add methods  to take in the name of the Data dictionary so the method can
	 *    know  which one to look up and  get the variables as of rightn now  
	 *    ("/shortInput.xml") is a place holder
	 * 2) A default value can be implimented 
	 * 3) Error handeling should also be implimented for the above
	 * 
	 * 
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	
	 public List<study> ListOfvariables() throws ParserConfigurationException, SAXException, IOException{
		
		
		List<study> studyPojo = new ArrayList<study>();

        
        try (InputStream inputStream = getClass().getResourceAsStream("/shortInput.xml");
			    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			    String contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));

    	Document doc1 = convertStringToDocument(contents);
    	doc1.getDocumentElement().normalize();

    	
    	NodeList nodeList = doc1.getElementsByTagName("variable");  
    	
    	/*
    	 * NOTE : This limitation to 10 iteration make the application faster for demonstration and testing 
    	 * purpose. in the real case it shoulld be uncommented
    	 */
    	
  	//for (int itr = 0; itr < nodeList.getLength(); itr++)
    		for (int itr = 0; itr < 10; itr++)
    	{  
        	
    		
    	study st= new study();	
    	Node node = nodeList.item(itr); 
    	st.setVariable_id(node.getAttributes().getNamedItem("id").toString());
    	
    	
    	if (node.getNodeType() == Node.ELEMENT_NODE)   
    	{  
    	Element eElement = (Element) node;  
    	st.setName(eElement.getElementsByTagName("name").item(0).getTextContent().toString());

    	st.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
    	}  
    	studyPojo.add(st);
    	}

    		System.out.println(studyPojo);
    		
		return studyPojo;  

        }
	}
	 
	 private static Document convertStringToDocument(String xmlStr) {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	        DocumentBuilder builder;  
	        try  
	        {  
	            builder = factory.newDocumentBuilder();  
	            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
	            return doc;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return null;
	    }
	 
	 
	 
	 
	

}
