package com.dharmonization;

import javax.xml.bind.JAXBException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
* The Data Transformation program implements an application that
* simply gets information from the dbgap Xml file located in the input folder and
*  produces a fhir model (valueset,conceptmap and code system) on  the various endpoints.
*
* @author  Anthony Ikeogu
* @version 1.0
* @since   2020-12-20 
*/


@ComponentScan("com.dharmonization")
@SpringBootApplication
public class DDentServiceApplication {

	public static void main(String[] args) throws JAXBException {
		SpringApplication.run(DDentServiceApplication.class, args);
		
	}	
}
