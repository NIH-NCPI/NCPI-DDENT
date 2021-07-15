package com.dharmonization.services;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.expression.ParseException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import com.dharmonization.entities.CodeSystem;
import com.dharmonization.entities.Compose;
import com.dharmonization.entities.Concept;
import com.dharmonization.entities.ConceptMap;
import com.dharmonization.entities.Contact;
import com.dharmonization.entities.Element;
import com.dharmonization.entities.Group;
import com.dharmonization.entities.Identifier;
import com.dharmonization.entities.Include;
import com.dharmonization.entities.Target;
import com.dharmonization.entities.Telecom;
import com.dharmonization.entities.Text;
import com.dharmonization.entities.ValueSet;
import com.dharmonization.entities.ValuesetConcept;
import com.dharmonization.entities.study1.nlp;
import com.dharmonization.entities.study1.study;
import com.dharmonization.entities.study1.studyInFhir;
import com.dharmonization.entities.study1.studyPlusNlp;

@Component
@Service
public class ClampExtraction {

	
	PojoPlusNlpServices pns= new PojoPlusNlpServices();
	XmlToPojoService xps= new XmlToPojoService();	
	List<studyInFhir> newfhirlist= new ArrayList<studyInFhir>();
	List<studyPlusNlp> newlist= new ArrayList<studyPlusNlp>();
	
	

	/**
	 * The "StudyandNlp" method takes the list of variables
	 * from the xml and on each of the variables uses thier description
	 * to get extra info from CLAMP (NLP). This methods output's an 
	 * object (studyPlusNlp) which is a combination of table variables and some clamp info
	 * 
	 * @return list of StudyPlusNLP(object) 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<studyPlusNlp> StudyandNlpInfo() throws 
	ParserConfigurationException, 
	SAXException, 
	IOException, 
	ParseException{

		
		List<study> hs = new ArrayList<>(); 
		hs.clear();
		newlist.clear();
	
		hs.addAll(xps.ListOfvariables());

		for (int i = 0; i < hs.size(); i++) {
			studyPlusNlp spn= new studyPlusNlp();
			spn.setName(hs.get(i).getName());
			spn.setDescription(hs.get(i).getDescription());
			String varId= hs.get(i).getVariable_id();
			spn.setVariable_id(varId.substring(4, 18));
			

        List <nlp> np=pns.getInfFromNlp(hs.get(i).getDescription());
        spn.setNlp(np);
        newlist.add(spn);

    }
		return newlist;
	}

	/*TODO:
    1) Replace the  hard coded values with passed on values through the methods
    2) Error handeling for wrong invocations
    3) Default  values.
	*/
	
	/**
	 * 
	 * @param nameOfDataDicFile
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document  docReader(String nameOfDataDicFile) throws ParserConfigurationException, SAXException, IOException {
		
		if(nameOfDataDicFile.endsWith(".xml")) {
			nameOfDataDicFile.replaceFirst(".xml", "");
		}
		else if(nameOfDataDicFile.isBlank()) {
			
			nameOfDataDicFile="phs000007.v32.pht000009.v2.ex0_7s.data_dict";
		}		
		
		// Location Of the XML document
        File file= new File("src/main/resources/"+ nameOfDataDicFile+".xml");
      
        // Extracting information from the file
    	DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
    	DocumentBuilder db=dbf.newDocumentBuilder();

    	Document doc =db.parse(file);
    	doc.getDocumentElement().normalize();
    		System.out.println("File to use :"+nameOfDataDicFile);
    	
		return doc;

	}
	
	/*TODO:
    1) Replace the  hard coded values with passed on values through the methods
    2) Error handeling for wrong invocations
    3) Default  values.
	*/
	
	/**
	 * Thiis Method Reads the  Documents (Variable Report)
	 * 
	 * @return document
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document  docReader2(String nameOfVarReptFile) throws ParserConfigurationException, SAXException, IOException {

		if(nameOfVarReptFile.endsWith(".xml")) {
			nameOfVarReptFile.replaceFirst(".xml", "");
		}
		else if(nameOfVarReptFile.isBlank()) {
			
			nameOfVarReptFile="phs000007.v32.pht000009.v2.p13.ex0_7s.var_report";
		}
		
		
		File file= new File("src/main/resources/"+nameOfVarReptFile+".xml");
		
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
    	DocumentBuilder db=dbf.newDocumentBuilder();
		
    	Document doc =db.parse(file);
    	doc.getDocumentElement().normalize();
    	System.out.println("File to use :"+nameOfVarReptFile);
		
    	return doc;
	}
	
	
	/**
	 * Fhir CodeSystem construction
	 * 
	 * @return codeSystem
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public CodeSystem FhirCodeSys()throws ParserConfigurationException, SAXException, IOException{
	  
		// fields 
		 CodeSystem codesys= new CodeSystem();
		 List<Concept> concept= new ArrayList<>();
		 
		 // list of study
		 List<study> hs2 = new ArrayList<>();
		 hs2.addAll(xps.ListOfvariables());
		 
		 // making a list of concept
		 for (int i = 0; i < hs2.size(); i++) {
			 Concept cpt= new Concept();
			String code= hs2.get(i).getVariable_id();
			cpt.setCode(code.substring(4, 18)); 
			//cpt.setDefinition(hs2.get(i).getDescription());
			//cpt.setCode(hs2.get(i).getVariable_id());
			cpt.setDisplay(hs2.get(i).getName());
			cpt.setDefinition(hs2.get(i).getDescription());
			
			concept.add(cpt);

	    }
		 String str="";
	    	Document doc =this.docReader(str);
	    	Document doc2 =this.docReader2(str);
	    	
	    	NodeList nodedescription= doc.getElementsByTagName("description");

	    	Node nodedescr=nodedescription.item(0);
	    	
	    	
	    	codesys.setResourceType("CodeSystem");
	    	codesys.setId(doc2.getDocumentElement().getAttribute("dataset_id"));
	    	codesys.setUrl("fhir/base/url/Codesystem/"+doc.getDocumentElement().getAttribute("id"));
	    	String version= doc.getDocumentElement().getAttribute("id");
	    	codesys.setVersion(version.substring(9));
	    	codesys.setName(doc2.getDocumentElement().getAttribute("name"));
	    	//codesys.setTitle(nodedescr.getTextContent()+", Study :"+doc.getDocumentElement().getAttribute("study_id")+"|"+"Table :"+doc.getDocumentElement().getAttribute("id"));
	    	codesys.setTitle(doc2.getDocumentElement().getAttribute("study_name"));
	    	codesys.setStatus("active");
	    	codesys.setExperimental(false);
	    	codesys.setPublisher("dbgap");
	    	codesys.setDescription(nodedescr.getTextContent());
	    	codesys.setCaseSensitive(true);
	    	codesys.setContent("complete");
	    	codesys.setCount(concept.size());
	    	codesys.setConcept(concept);
	return codesys;
	
    }
	
	
	/**
	 * Fhir ValueSet construction
	 * 
	 * @return valueSet
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public ValueSet FhirValueSet() throws ParserConfigurationException, SAXException, IOException {
		
		Compose cmp = new Compose();
		Include inl = new Include();
		ValueSet vset = new ValueSet();
		
		
		List<ValuesetConcept> concept= new ArrayList<>();
		List<Include> inclList= new ArrayList<>();
		
		
		 // study
		 List<study> hs3 = new ArrayList<>();
		 hs3.addAll(xps.ListOfvariables());
		 
		 // making a list of concept
		 for (int i = 0; i < hs3.size(); i++) {
			 ValuesetConcept cpt = new ValuesetConcept();
			 String code= hs3.get(i).getVariable_id();
			 cpt.setCode(code.substring(4, 18));
			// cpt.setDefinition(hs3.get(i).getDescription());
			 cpt.setDisplay(hs3.get(i).getName());
			
			concept.add(cpt);
			
			String str="";
			Document doc =this.docReader(str);
	    	
	    	NodeList nodedescription= doc.getElementsByTagName("description");
	    	Node nodedescr=nodedescription.item(0);
	    	
	    	vset.setResourceType("ValueSet");
	    	//vset.setId("Study "+doc.getDocumentElement().getAttribute("study_id")+" | "+"Table "+doc.getDocumentElement().getAttribute("id"));
	    	vset.setId( doc.getDocumentElement().getAttribute("id"));
	    	vset.setUrl("fhir/base/url/ValueSet/"+doc.getDocumentElement().getAttribute("id"));
	    	// use substring to cut off the first 9 digits leaving you with the version number
	    	String version= doc.getDocumentElement().getAttribute("id");
	    	vset.setVersion(version.substring(9));
	    	//vset.setName("Study "+doc.getDocumentElement().getAttribute("study_id")+" | "+"Table "+doc.getDocumentElement().getAttribute("id"));
	    	vset.setName(doc.getDocumentElement().getAttribute("id"));
	    	//vset.setTitle("Variables for Table : "+doc.getDocumentElement().getAttribute("id"));
	    	vset.setTitle(doc.getDocumentElement().getAttribute("id"));
	    	vset.setStatus("active");
	    	vset.setExperimental(false);
	    	vset.setPublisher("dbgap");
	    	vset.setDescription(nodedescr.getTextContent());
	    	
	    	inl.setConcept(concept);
	    	inl.setSystem("http://fhir/base/url/CodeSystem/"+doc.getDocumentElement().getAttribute("id"));
	    	
	    	cmp.setInclude(inclList);
	    	vset.setCompose(cmp);
	    	
	    }inclList.add(inl);
		
		return vset;
	}
	
	/**
	 * Fhir ConceptMap construction
	 * 
	 * @return ConceptMap
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
     public ConceptMap FhirConceptMap() throws ParserConfigurationException, SAXException, IOException, ParseException {
		
        //Map of value set authority center
		Map<Integer, String> vsac = new HashMap<Integer, String>();
		
		// Inserting the Elements 
        vsac.put(1, "http://loinc.org"); //LOINC
        vsac.put(2, "http://www.nlm.nih.gov/research/umls/rxnorm"); //RXNORM
        vsac.put(3, "http://snomed.info/sct"); //SNOMEDCT
        vsac.put(4, "http://www.nlm.nih.gov/research/umls"); //UMLS
        vsac.put(5, "http://ncimeta.nci.nih.gov"); //NCI
        vsac.put(6, "http://unknown.valueSet.authority.center.gov"); //NCI

		//Group grp= new Group();
		Group grp1= new Group();
		Group grp2= new Group();
		Group grp3= new Group();
		
		//Group grp5= new Group();
		List<Group> ListOfGroup1= new ArrayList<>();
		List<Group> ListOfGroup2= new ArrayList<>();
		List<Group> ListOfGroup3= new ArrayList<>();
		List<Group> ListOfGroup4= new ArrayList<>();
		//List<Group> ListOfGroup5= new ArrayList<>();
		Text tx=new Text();
		
		Contact ctc= new Contact();
		Telecom tlc= new Telecom();
		Identifier idf= new Identifier();
		ConceptMap cptm= new ConceptMap();
		
		
		List<Element> Lele1= new ArrayList<>();
		List<Element> Lele2= new ArrayList<>();
		List<Element> Lele3= new ArrayList<>();
		
		
		List<Contact> Lctc= new ArrayList<>();
		List<Telecom> Ltc= new ArrayList<>();
		List<Group> Lgrp= new ArrayList<>();
		List<studyPlusNlp> newlist2= new ArrayList<studyPlusNlp>();
		List<study> hs2 = new ArrayList<>(); 
		hs2.clear();
		
		hs2.addAll(xps.ListOfvariables());
		
		for (int i = 0; i < hs2.size(); i++) {
			studyPlusNlp spn= new studyPlusNlp();
			spn.setName(hs2.get(i).getName());
			spn.setDescription(hs2.get(i).getDescription());
			spn.setVariable_id(hs2.get(i).getVariable_id());

        List <nlp> np=pns.getInfFromNlp(hs2.get(i).getDescription());
        spn.setNlp(np);
        newlist2.add(spn);
    }
		String str="";
		Document doc =this.docReader(str);
    	
		cptm.setResourceType("ConceptMap");
    	tx.setDiv("<div xmlns=\"http://www.w3.org/1999/xhtml\"></div>");
    	tx.setStatus("empty");
    	cptm.setText(tx);
		cptm.setId(doc.getDocumentElement().getAttribute("id"));
		cptm.setUrl("fhir/base/url/Codesystem/"+doc.getDocumentElement().getAttribute("id"));
		idf.setSystem("http://localhost:8000//Codesystem/"+doc.getDocumentElement().getAttribute("id"));
		idf.setValue(doc.getDocumentElement().getAttribute("id"));
		cptm.setIdentifier(idf);
		
		// use substring to cut off the first 9 digits leaving you with the version number
    	String version= doc.getDocumentElement().getAttribute("id");
		cptm.setVersion(version.substring(10));
		//cptm.setName("Study "+doc.getDocumentElement().getAttribute("study_id")+" | "+"Table "+doc.getDocumentElement().getAttribute("id"));
		//cptm.setName("vr_dates_2019_a_1175s");
		cptm.setStatus("active");
		cptm.setExperimental(false);
		//cptm.setDate(doc.getDocumentElement().getAttribute("date_created"));
		cptm.setPublisher("dbgap");
		
		ctc.setName("Anthony Ikeogu");
	
		tlc.setSystem("url");
		tlc.setValue("vumc.org");
		Ltc.add(tlc);
		
		ctc.setTelecom(Ltc);
		Lctc.add(ctc);
		
		cptm.setContact(Lctc);
		cptm.setDescription("A mapping between the FHIR and dbgap v1 Address Use Code systems");
		cptm.setPurpose("To help implementers map  dbgab table variables to FHIR");
		cptm.setSourceCanonical("https://www.ncbi.nlm.nih.gov/projects/gap/cgi-bin/study.cgi?study_id=phs000007.v31.p12");
		cptm.setTargetCanonical("https://www.ncbi.nlm.nih.gov/projects/gap/cgi-bin/dataset.cgi?study_id=phs000007.v31.p12&pht=9");
		

		//  this  is for  Commons
		for (int i = 0; i < newlist2.size(); i++) {
			Element emt1 =  new Element();
			
			
			
			List<Target> target= new ArrayList<>();
			
			List<nlp> nlps = new ArrayList<>(); 
			nlps.addAll(newlist2.get(i).getNlp());

			
			for (nlp nlpp : nlps) {
				Target tgt= new Target();

				String strnlp=nlpp.getCui();
				
				if(strnlp != "null" && !strnlp.equals(null) && strnlp != "") {
					
					
					
					//cui splitter					
					  String myString = nlpp.getCui();
				      String[] myArray = myString.split(",");
				      List <String> myList = Arrays.asList(myArray);
				      for (String cuis : myList) {
				      	if(cuis.startsWith("C")) {
				      		
				      	//set code  for element
							String code =newlist2.get(i).getVariable_id();
							emt1.setCode(code.substring(4, 18));
							emt1.setDisplay(newlist2.get(i).getDescription());
	
						
						tgt.setCode(cuis);
				      	tgt.setEquivalence("relatedto");
				      	tgt.setComment("Semantic :"+nlpp.getSemantics()+", Entity :"+nlpp.getEntity()+ ", Concept Proberbility :"+ nlpp.getConcept_prob());
				      	target.add(tgt);				
				      }
				}
                     emt1.setTarget(target);
     
			}

			// check for duplicates
			if(!Lele1.contains(emt1)) {
				Lele1.add(emt1);
			}

			//removes all null element
			for(int ii = 0; ii < Lele1.size(); ii++) {
				try {
					if(	Lele1.get(ii).getCode().equals(null)) {
				}
					
					}
					catch(Exception e) {
						Lele1.remove(ii);
						System.out.println("null element removed");
					}
            }
		}
	}
		grp1.setSource("https://ftp.ncbi.nlm.nih.gov/dbgap/studies/phs000007/phs000007.v31.p12/pheno_variable_summaries/phs000007.v31.pht000009.v2.p12.ex0_7s.var_report.xml");
		String version2= doc.getDocumentElement().getAttribute("id");
		grp1.setSourceVersion(version2.substring(11));
		grp1.setTarget(vsac.get(4));
		grp1.setTargetVersion("1.0");
		grp1.setElement(Lele1);

		ListOfGroup1.add(grp1);
		
		
		
	    //  this  is for  SNOMED
		for (int i = 0; i < newlist2.size(); i++) {
			Element emt2 =  new Element();

			List<Target> target= new ArrayList<>();
			
			List<nlp> nlps = new ArrayList<>(); 
			nlps.addAll(newlist2.get(i).getNlp());

			
			for (nlp nlpp : nlps) {
				Target tgt= new Target();

				String strnlp=nlpp.getCui();
				
				if(strnlp != "null" & !strnlp.equals(null) & strnlp != "") {
					//cui splitter					
					  String myString = nlpp.getCui();
				      String[] myArray = myString.split(",");
				      List <String> myList = Arrays.asList(myArray);
				      for (String cuis : myList) {
				      	if(cuis.startsWith("SNOMEDCT_US")) {
				      		
				      	//set code  for element
							String code =newlist2.get(i).getVariable_id();
							emt2.setCode(code.substring(4, 18));
							emt2.setDisplay(newlist2.get(i).getDescription());
				      		
						String cuiNumberExtraction =cuis;
					    tgt.setCode(cuiNumberExtraction.substring(12,20));
				      	tgt.setEquivalence("relatedto");
				      	tgt.setComment("Semantic :"+nlpp.getSemantics()+", Entity :"+nlpp.getEntity()+ ", Concept Proberbility :"+ nlpp.getConcept_prob());
				      	target.add(tgt);				
				      }

				}
				      emt2.setTarget(target);
			}
			
				// check for duplicates
				if(!Lele2.contains(emt2)) {
					Lele2.add(emt2);
				}
				
			for(int ii = 0; ii < Lele2.size(); ii++) {
				try {
					if(	Lele2.get(ii).getCode().equals(null)) {
				}
					}
					catch(Exception e) {
						Lele2.remove(ii);
						System.out.println("null Snowmed element removed");
					}
            }
		}

	}
		grp2.setSource("https://ftp.ncbi.nlm.nih.gov/dbgap/studies/phs000007/phs000007.v31.p12/pheno_variable_summaries/phs000007.v31.pht000009.v2.p12.ex0_7s.var_report.xml");
		String version22= doc.getDocumentElement().getAttribute("id");
		grp2.setSourceVersion(version22.substring(11));
		grp2.setTarget(vsac.get(3));
		grp2.setTargetVersion("1.0");
		grp2.setElement(Lele2);

		ListOfGroup2.add(grp2);
		
		
		
		
	    //  this  is for  RxNorm
		for (int i = 0; i < newlist2.size(); i++) {
			Element emt3 =  new Element();

			List<Target> target= new ArrayList<>();
			
			List<nlp> nlps = new ArrayList<>(); 
			nlps.addAll(newlist2.get(i).getNlp());

			
			for (nlp nlpp : nlps) {
				Target tgt= new Target();

				String strnlp=nlpp.getCui();
				
				if(strnlp != "null" & !strnlp.equals(null) & strnlp != "") {
					//cui splitter					
					  String myString = nlpp.getCui();
				      String[] myArray = myString.split(",");
				      List <String> myList = Arrays.asList(myArray);
				      for (String cuis : myList) {
				      	if(cuis.startsWith("RxNorm")) {
				      		
				      	//set code  for element
							String code =newlist2.get(i).getVariable_id();
							emt3.setCode(code.substring(4, 18));
							emt3.setDisplay(newlist2.get(i).getDescription());
				      		
						String cuiNumberExtraction =cuis;
				      	tgt.setCode(cuiNumberExtraction.substring(8,15));
				      	tgt.setEquivalence("relatedto");
				      	tgt.setComment("Semantic :"+nlpp.getSemantics()+", Entity :"+nlpp.getEntity()+ ", Concept Proberbility :"+ nlpp.getConcept_prob());
				      	target.add(tgt);				
				      }
				}
				      emt3.setTarget(target);
			}

				// check for duplicates
				if(!Lele3.contains(emt3)) {
					Lele3.add(emt3);
				}
				
			for(int ii = 0; ii < Lele3.size(); ii++) {
				try {
					if(	Lele3.get(ii).getCode().equals(null)) {
				}
					}
					catch(Exception e) {
						Lele3.remove(ii);
						System.out.println("null Rxnorm element removed");
					}
            }
		}

	}
		grp3.setSource("https://ftp.ncbi.nlm.nih.gov/dbgap/studies/phs000007/phs000007.v31.p12/pheno_variable_summaries/phs000007.v31.pht000009.v2.p12.ex0_7s.var_report.xml");
		grp3.setSourceVersion(version22.substring(11));
		grp3.setTarget(vsac.get(2));
		grp3.setTargetVersion("1.0");
		grp3.setElement(Lele3);


		ListOfGroup3.add(grp3);

		
		Lgrp.addAll(ListOfGroup1);
		Lgrp.addAll(ListOfGroup2);
		Lgrp.addAll(ListOfGroup3);
		Lgrp.addAll(ListOfGroup4);
		
		
		cptm.setGroup(Lgrp);
		
		
		return cptm;
}
	
	/**
	 *  Fhir dbgap Concept Unique Identifier (CUI) version CodeSystem construction
	 * 
	 * @return Codesystem
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	public CodeSystem dbgapCuiCodeSys()throws ParserConfigurationException, SAXException, IOException, ParseException{
		
		// fields 
	    CodeSystem codesys= new CodeSystem();
		List<Concept> concept= new ArrayList<>();
        List<studyPlusNlp> newlist3= new ArrayList<studyPlusNlp>();
		List<study> hs2 = new ArrayList<>(); 
		hs2.clear();

        // Elements are added using add() method 
		hs2.addAll(xps.ListOfvariables());
		
		for (int i = 0; i < hs2.size(); i++) {
			studyPlusNlp spn= new studyPlusNlp();
			spn.setName(hs2.get(i).getName());
			spn.setDescription(hs2.get(i).getDescription());
			spn.setVariable_id(hs2.get(i).getVariable_id());

        List <nlp> np=pns.getInfFromNlp(hs2.get(i).getDescription());
        spn.setNlp(np);
        newlist3.add(spn);
    }
		String str="";
		Document doc =this.docReader(str);
  
    	codesys.setResourceType("CodeSystem");
    	codesys.setId("Concept unique identifier of "+"Study "+doc.getDocumentElement().getAttribute("study_id")+" | "+"Table "+doc.getDocumentElement().getAttribute("id"));
    	codesys.setUrl("http://hl7.org/fhir/R4/codesystem.html");
    	codesys.setName("Concept unique identifier of "+"Study "+doc.getDocumentElement().getAttribute("study_id"));
    	codesys.setTitle("Concept unique identifier ");
    	codesys.setStatus("active");
    	codesys.setExperimental(false);
    	codesys.setDescription("Concept unique identifier of the description of variables in "+" Study "+doc.getDocumentElement().getAttribute("study_id")+"|"+"Table "+doc.getDocumentElement().getAttribute("id"));
    	codesys.setCaseSensitive(true);
    	codesys.setContent("supplement");
    	codesys.setConcept(concept);
    	
    	
    	for (int i = 0; i < newlist3.size(); i++) {
			List<nlp> nlps = new ArrayList<>(); 
			nlps.addAll(newlist3.get(i).getNlp());
			for (nlp nlpp : nlps) {
				 Concept cpt= new Concept();
				cpt.setCode(nlpp.getCui());
				cpt.setDisplay(nlpp.getCui());
				concept.add(cpt);
			}
		}
    	codesys.setCount(concept.size());
        return codesys;
	}
	
	
	/**
	 * Fhir dbgap Concept Unique Identifier (CUI) version ValueSet construction
	 * 
	 * @return ValueSet
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	
	public ValueSet dbgapCuiValueSet() throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		Compose cmp = new Compose();
		Include inl = new Include();
		ValueSet vset = new ValueSet();
		
		List<ValuesetConcept> concept= new ArrayList<>();
		List<studyPlusNlp> newlist3= new ArrayList<studyPlusNlp>();
		List<Include> inclList= new ArrayList<>();
		
		
		 // study
		 List<study> hs3 = new ArrayList<>();
		 hs3.addAll(xps.ListOfvariables());
		 
		 // making a list of concept
		 List<study> hs2 = new ArrayList<>(); 
			hs2.clear();

	        // Elements are added using add() method 
			hs2.addAll(xps.ListOfvariables());

			
			for (int i = 0; i < hs2.size(); i++) {
				studyPlusNlp spn= new studyPlusNlp();
				spn.setName(hs2.get(i).getName());
				spn.setDescription(hs2.get(i).getDescription());
				spn.setVariable_id(hs2.get(i).getVariable_id());

	        List <nlp> np=pns.getInfFromNlp(hs2.get(i).getDescription());
	        spn.setNlp(np);
	        newlist3.add(spn);

	    }
	    	
	        // Extracting information from the file
			String str="";
	        Document doc =this.docReader(str);
	    	
	    	vset.setResourceType("ValueSet");
	    	vset.setId("Concept unique identifier of "+"Study "+doc.getDocumentElement().getAttribute("study_id")+" | "+"Table "+doc.getDocumentElement().getAttribute("id"));
	    	vset.setUrl("http://hl7.org/fhir/R4/valueset.html");
	    
	    	vset.setVersion("1.0");
	    	vset.setName("Concept Unique Identifier of Study "+doc.getDocumentElement().getAttribute("study_id"));
	    	vset.setTitle("Concept unique identifier of Variables for Table : "+doc.getDocumentElement().getAttribute("id"));
	    	vset.setStatus("active");
	    	vset.setExperimental(false);
	    	vset.setPublisher("dbgap");
	    	vset.setDescription("Concept unique identifier of the description of variables in "+" Study "+doc.getDocumentElement().getAttribute("study_id")+"|"+"Table "+doc.getDocumentElement().getAttribute("id"));
	    	
	    	inl.setConcept(concept);
	    	
	    	cmp.setInclude(inclList);
	    	vset.setCompose(cmp);
	    	
	    	for (int i = 0; i < newlist3.size(); i++) {
				List<nlp> nlps = new ArrayList<>(); 
				nlps.addAll(newlist3.get(i).getNlp());
				
				for (nlp nlpp : nlps) {
					ValuesetConcept cpt= new ValuesetConcept();
					
					cpt.setCode(nlpp.getCui());
					cpt.setDisplay(nlpp.getCui());
					
					concept.add(cpt);
				}
			}
	    	
	    inclList.add(inl);
		
		return vset;
		
		
		
	}
	
	
}
