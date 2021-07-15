# NCPI-DDENT
NCPI Data Dictionary Extraction and NLP Tool

![NCPI Data Dictionary Extraction and NLP Tool](/img/ddent-overview.png)
NCPI DDENT is a prototype web service intended to extract variable metadata from DbGAP studies and build FHIR ConceptMaps linking those variables to healthcare terms such as SNOMED, LOINC, etc. When used with an NLP system to generate related [Concept Unique Identifiers (CUIs)](https://www.nlm.nih.gov/research/umls/new_users/online_learning/Meta_005.html) to populate secondary CodeSystems, the two can be mapped via the [ConceptMap](http://hl7.org/fhir/R4/conceptmap.html) allowing end users to identify relevant study variables based on the FHIR [$translate operation](http://hl7.org/fhir/R4/conceptmap-operation-translate.html).

While work on the prototype has stopped, the interst in mapping study specific variables to common terminologies is very real and is likely to be revisted once some of the initial work has been completed migrating DbGAP study information into FHIR. 

## System Requirements
### API
The web service itself is written using [Spring Boot](https://spring.io/projects/spring-boot) and requires [Maven](https://maven.apache.org) to build. Spring Boot is a Java framework and thus, requires a modern version of Java.  (As of July 15, 2021, the current version of Maven in Ubuntu 20.04 is not compatible with the current version of Java, so it's probably best to stick with an earlier version).

### NLP
In addition parsing DbGAP files to generate FHIR code systems for a given dataset, we have deloped a prototype API that interacts with [CLAMP](https://clamp.uth.edu) to generate CUIs for the variable descriptions, which can then be employed as the secondary FHIR code systems. 

CLAMP is the product of a group from the [University of Texas Health Science Center at Houston, School of Biomedical Informatics](uth.edu) and requires permission from the responsible parties there to share our prototype. TBD -- Get contact information for [CLAMP](https://clamp.uth.edu) 

## Installation & Starting Services
### NLP Service
To run the web service, users will need to have downloaded the companion NLP service which requires special permission to use due to the license restrictions from the CLAMP team. Once permission has been grented, this will be delivered as a simple Docker image which be loaded as follows:

Download image and change to the directory containing the file and run the following command
> docker load --input ws_clamp.tar

To verify that the image is loaded run the following command
> docker images

To start the NLP service, run the following command
> docker run -dit -p 8080:8080 ws_clamp:1.0

### DDENT Service
Clone the repository
> git clone https://github.com/NIH-NCPI/NCPI-DDENT

Switch to the new directory and build the snapshot jar file
> mvn clean install

To run the service locally, cd into the subdirectory, target, and run the following command
> java -jar data-harmonization-0.0.1-SNAPSHOT.jar

## Web Service Workflow
For this version of the prototype, the workflow is shown below. 

When running the service as is on a local system, the base endpoint is http://localhost:4040

### Ingest Into FHIR
[base]/api/InjestIntoFhir
Input required:
* strDataDicUrl : The URL to a DbGAP study variable summary, such as https://ftp.ncbi.nlm.nih.gov/dbgap/studies/phs000007/phs000007.v32.p13/pheno_variable_summaries/phs000007.v32.pht000009.v2.ex0_7s.data_dict.xml
* strdocDataDicname : Name to be assigned to the data dictionary
* strVarReportUrl : URL to the related DbGAP variable report such as https://ftp.ncbi.nlm.nih.gov/dbgap/studies/phs000007/phs000007.v32.p13/pheno_variable_summaries/phs000007.v32.pht000009.v2.p13.ex0_7s.var_report.xml
* strdocVarReportname : Name to be assigned to the variable report

If successful, each of the CodeSystems generated will be stored inside the FHIR server alongside the ConceptMap. Users can also observe those FHIR structures from the console output during runtime. 
