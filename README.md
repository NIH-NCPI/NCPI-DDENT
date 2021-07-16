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
### FHIR Service
The service uses standard FHIR protocols to store data, and as such, any FHIR server should work. The FHIR Server must support ConceptMap and it's operation, $translate. These are mature FHIR components and are likely to work properly in most FHIR offerings.

For those interested in a free, open source FHIR solution to test with, check out [HAPI](https://hapifhir.io/) [This](https://github.com/smart-on-fhir/hapi) may also prove helpful in getting it jumpstarted. 

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

#### Run from console using Java
To run the service locally, cd into the subdirectory, target, and run the following command
> java -jar ncpi-ddent-0.0.1-SNAPSHOT.jar

#### Build and run using Docker
Once the build command above has completed, use the following command to build the Docker image:
> docker build -t ddent-service .

Once completed, it can be run using the following command
> docker run -dp 4040:4040 ddent-service

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

## DDENT - Service Details
Convert data from dbgap study to Fhir model 
- GitRepo https://github.com/NIH-NCPI/NCPI-DDENT

### API Overview
![](/img/ddent-details.jpg)

DDENT is a web service and as such, there is no front end except for swagger, which can be used to play around with the API using a browser. 
![](/img/ddent-swagger.png)

The API calls are listed below and use the base url: localhost:4040

1) http://localhost:4040/api/variables
The Variables extracted from the XML (file or dbgap db) in JSON format.

2) http://localhost:4040/api/modifiedvariables
Using the CLAMP api to get extra information like (Cui).

3) http://localhost:4040/api/CodeSystem
Return the CodeSystem that was the product of the most recent trasform

4) http://localhost:4040/api/ValueSet
Return the ValueSet that was the product of the most recent trasform

5) http://localhost:4040/api/CuiCodeSystem
Return the CodeSystem for the CUI returns from NLP from the most recent transform

6) http://localhost:4040/api/CuiValueSet
Return the ValueSet associated with CUI returns from NLP from the most recent transform

7) http://localhost:4040/api/ConceptMap
Returns the ConceptMap generated as a result of the most recent transform.

8) http://localhost:4040/api/InjestIntoFhir
This is the transform/ingest function that kicks the whole thing off. More information about this can be found above. 

### API Roadmap
![](/img/ddent-roadmap.png)
This roadmap provides an overview of components of the DDENT ecosystem and how they relate to another at the function level. Please note that when this image was created, DDENT was called DataHarmonization.  

### Ingestion Process Example
This process will be brocken down into two sections
1) Injest request
2) Data Transformation Service output.

Urls
Using Swagger incase of lack of a frontend.
 http://localhost:4040/swagger-ui.html
 
Using a different application use this end point to make a POST Call with this four params

      Param Name       : Param Definition
 - strDataDicUrl       :  Data Dictionary Url
 - strdocDataDicname   :  Data Dictionary name
 - strVarReportUrl     : Variable Report Url
 - strdocVarReportname : Variable Report name

Endpoint:
 http://localhost:4040/api/InjestIntoFhir
 
#### Swagger Injest Request
![](/img/request.png)

#### Data Transformation service output

```json
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.3.5.RELEASE)

2021-02-27 10:34:02.521  INFO 99659 --- [           main] c.d.DataHarmonizationServiceApplication  : Starting DataHarmonizationServiceApplication on Anthony-Mosess-MacBook-Pro.local with PID 99659 (/Users/ikeoguan/Documents/GitHub/data-harmonization-service/target/classes started by ikeoguan in /Users/ikeoguan/Documents/GitHub/data-harmonization-service)
2021-02-27 10:34:02.524  INFO 99659 --- [           main] c.d.DataHarmonizationServiceApplication  : No active profile set, falling back to default profiles: default
2021-02-27 10:34:03.487  INFO 99659 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 4040 (http)
2021-02-27 10:34:03.494  INFO 99659 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-02-27 10:34:03.494  INFO 99659 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.39]
2021-02-27 10:34:03.566  INFO 99659 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-02-27 10:34:03.567  INFO 99659 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 992 ms
2021-02-27 10:34:03.812  INFO 99659 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2021-02-27 10:34:04.372  INFO 99659 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 2 endpoint(s) beneath base path '/actuator'
2021-02-27 10:34:04.411  INFO 99659 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 4040 (http) with context path ''
2021-02-27 10:34:04.425  INFO 99659 --- [           main] c.d.DataHarmonizationServiceApplication  : Started DataHarmonizationServiceApplication in 2.152 seconds (JVM running for 7.56)
2021-02-27 10:34:04.838  INFO 99659 --- [on(1)-127.0.0.1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2021-02-27 10:34:04.838  INFO 99659 --- [on(1)-127.0.0.1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2021-02-27 10:34:04.843  INFO 99659 --- [on(1)-127.0.0.1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 5 ms
2021-02-27 10:34:13.375  INFO 99659 --- [nio-4040-exec-1] o.apache.tomcat.util.http.parser.Cookie  : A cookie header was received [Week (Default)] that contained an invalid cookie. That cookie will be ignored.
 Note: further occurrences of this error will be logged at DEBUG level.
ok  done  downloading the XML File
Data Dictionary Downloaded
ok  done  downloading the XML File
Var Report Downloaded
File to use :DataDicStudyphs000007
File to use :VarReportStudyphs000007
[[ variable_id=id="phv00000479.v1", name=MF4, description=RELATIVE WEIGHT, EXAM 1], [ variable_id=id="phv00000482.v1", name=MF8, description=HISTORY OF ACUTE INFECTIONS, EXAM 1], [ variable_id=id="phv00000483.v1", name=MF9, description=HISTORY OF RHEUMATIC FEVER, EXAM 1], [ variable_id=id="phv00000484.v1", name=MF10, description=HISTORY OF ALLERGY OR ASTHMA, EXAM 1], [ variable_id=id="phv00000485.v1", name=MF11, description=HISTORY OF CHRONIC ARTHRITIS AND RHEUMATISM, EXAM 1], [ variable_id=id="phv00000486.v1", name=MF12, description=HISTORY OF THYROID DISEASE, EXAM 1], [ variable_id=id="phv00000487.v1", name=MF13, description=HISTORY OF HYPERTENSION, EXAM 1], [ variable_id=id="phv00000488.v1", name=MF14, description=HISTORY OF ENLARGED HEART, EXAM 1], [ variable_id=id="phv00000489.v1", name=MF15, description=HISTORY OF NERVOUS HEART, EXAM 1], [ variable_id=id="phv00000490.v1", name=MF16, description=HISTORY OF PERICARDITIS, EXAM 1]]
File to use :phs000007.v32.pht000009.v2.ex0_7s.data_dict
null element removed
null element removed
null Snowmed element removed
null Snowmed element removed
null Snowmed element removed
null Snowmed element removed
null Rxnorm element removed
null Rxnorm element removed
null Rxnorm element removed
null Rxnorm element removed
null Rxnorm element removed
null Rxnorm element removed
null Rxnorm element removed
null Rxnorm element removed
null Rxnorm element removed
null Rxnorm element removed
null Rxnorm element removed
null Rxnorm element removed
null Rxnorm element removed
ConceptMap About to ingest
ConceptMap ingested in Fhir 201
Response from  ConceptMap  Ingestion
---------------------------------------------------------------------
{
  "resourceType": "ConceptMap",
  "id": "5002",
  "meta": {
    "versionId": "1",
    "lastUpdated": "2021-02-27T17:34:18.887+00:00"
  },
  "text": {
    "status": "empty",
    "div": "<div xmlns=\"http://www.w3.org/1999/xhtml\"/>"
  },
  "url": "fhir/base/url/Codesystem/pht000009.v2",
  "identifier": {
    "system": "http://localhost:8000//Codesystem/pht000009.v2",
    "value": "pht000009.v2"
  },
  "version": "v2",
  "status": "active",
  "experimental": false,
  "publisher": "dbgap",
  "contact": [ {
    "name": "Anthony Ikeogu",
    "telecom": [ {
      "system": "url",
      "value": "vumc.org"
    } ]
  } ],
  "description": "A mapping between the FHIR and dbgap v1 Address Use Code systems",
  "purpose": "To help implementers map  dbgab table variables to FHIR",
  "sourceCanonical": "https://www.ncbi.nlm.nih.gov/projects/gap/cgi-bin/study.cgi?study_id=phs000007.v31.p12",
  "targetCanonical": "https://www.ncbi.nlm.nih.gov/projects/gap/cgi-bin/dataset.cgi?study_id=phs000007.v31.p12&pht=9",
  "group": [ {
    "source": "https://ftp.ncbi.nlm.nih.gov/dbgap/studies/phs000007/phs000007.v31.p12/pheno_variable_summaries/phs000007.v31.pht000009.v2.p12.ex0_7s.var_report.xml",
    "sourceVersion": "2",
    "target": "http://www.nlm.nih.gov/research/umls",
    "targetVersion": "1.0",
    "element": [ {
      "code": "phv00000479.v1",
      "display": "RELATIVE WEIGHT, EXAM 1",
      "target": [ {
        "code": "C1262477",
        "equivalence": "relatedto",
        "comment": "Semantic :test, Entity :WEIGHT, EXAM, Concept Proberbility :0.991"
      } ]
    }, {
      "code": "phv00000482.v1",
      "display": "HISTORY OF ACUTE INFECTIONS, EXAM 1",
      "target": [ {
        "code": "C0275518",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :ACUTE INFECTIONS, Concept Proberbility :0.999"
      } ]
    }, {
      "code": "phv00000484.v1",
      "display": "HISTORY OF ALLERGY OR ASTHMA, EXAM 1",
      "target": [ {
        "code": "C0004096",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :ASTHMA, Concept Proberbility :0.956"
      } ]
    }, {
      "code": "phv00000485.v1",
      "display": "HISTORY OF CHRONIC ARTHRITIS AND RHEUMATISM, EXAM 1",
      "target": [ {
        "code": "C0263680",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :CHRONIC ARTHRITIS, Concept Proberbility :0.999"
      } ]
    }, {
      "code": "phv00000486.v1",
      "display": "HISTORY OF THYROID DISEASE, EXAM 1",
      "target": [ {
        "code": "C0040128",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :THYROID DISEASE, Concept Proberbility :0.988"
      } ]
    }, {
      "code": "phv00000487.v1",
      "display": "HISTORY OF HYPERTENSION, EXAM 1",
      "target": [ {
        "code": "C0020538",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :HYPERTENSION, Concept Proberbility :0.979"
      } ]
    }, {
      "code": "phv00000488.v1",
      "display": "HISTORY OF ENLARGED HEART, EXAM 1",
      "target": [ {
        "code": "C0018800",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :ENLARGED HEART, Concept Proberbility :0.935"
      } ]
    }, {
      "code": "phv00000490.v1",
      "display": "HISTORY OF PERICARDITIS, EXAM 1",
      "target": [ {
        "code": "C0031046",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :PERICARDITIS, Concept Proberbility :0.991"
      } ]
    } ]
  }, {
    "source": "https://ftp.ncbi.nlm.nih.gov/dbgap/studies/phs000007/phs000007.v31.p12/pheno_variable_summaries/phs000007.v31.pht000009.v2.p12.ex0_7s.var_report.xml",
    "sourceVersion": "2",
    "target": "http://snomed.info/sct",
    "targetVersion": "1.0",
    "element": [ {
      "code": "phv00000482.v1",
      "display": "HISTORY OF ACUTE INFECTIONS, EXAM 1",
      "target": [ {
        "code": "63171007",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :ACUTE INFECTIONS, Concept Proberbility :0.999"
      } ]
    }, {
      "code": "phv00000484.v1",
      "display": "HISTORY OF ALLERGY OR ASTHMA, EXAM 1",
      "target": [ {
        "code": "19596700",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :ASTHMA, Concept Proberbility :0.956"
      } ]
    }, {
      "code": "phv00000485.v1",
      "display": "HISTORY OF CHRONIC ARTHRITIS AND RHEUMATISM, EXAM 1",
      "target": [ {
        "code": "35908007",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :CHRONIC ARTHRITIS, Concept Proberbility :0.999"
      } ]
    }, {
      "code": "phv00000486.v1",
      "display": "HISTORY OF THYROID DISEASE, EXAM 1",
      "target": [ {
        "code": "14304000",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :THYROID DISEASE, Concept Proberbility :0.988"
      } ]
    }, {
      "code": "phv00000487.v1",
      "display": "HISTORY OF HYPERTENSION, EXAM 1",
      "target": [ {
        "code": "38341003",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :HYPERTENSION, Concept Proberbility :0.979"
      } ]
    }, {
      "code": "phv00000488.v1",
      "display": "HISTORY OF ENLARGED HEART, EXAM 1",
      "target": [ {
        "code": "8186001]",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :ENLARGED HEART, Concept Proberbility :0.935"
      } ]
    }, {
      "code": "phv00000490.v1",
      "display": "HISTORY OF PERICARDITIS, EXAM 1",
      "target": [ {
        "code": "3238004]",
        "equivalence": "relatedto",
        "comment": "Semantic :problem, Entity :PERICARDITIS, Concept Proberbility :0.991"
      } ]
    } ]
  }, {
    "source": "https://ftp.ncbi.nlm.nih.gov/dbgap/studies/phs000007/phs000007.v31.p12/pheno_variable_summaries/phs000007.v31.pht000009.v2.p12.ex0_7s.var_report.xml",
    "sourceVersion": "2",
    "target": "http://www.nlm.nih.gov/research/umls/rxnorm",
    "targetVersion": "1.0",
    "element": [ {
      "code": "phv00000483.v1",
      "display": "HISTORY OF RHEUMATIC FEVER, EXAM 1",
      "target": [ {
        "code": "1023414",
        "equivalence": "relatedto",
        "comment": "Semantic :drug, Entity :RHEUMATIC FEVER, Concept Proberbility :null"
      } ]
    } ]
  } ]
}
---------------------------------------------------------------------
[[ variable_id=id="phv00000479.v1", name=MF4, description=RELATIVE WEIGHT, EXAM 1], [ variable_id=id="phv00000482.v1", name=MF8, description=HISTORY OF ACUTE INFECTIONS, EXAM 1], [ variable_id=id="phv00000483.v1", name=MF9, description=HISTORY OF RHEUMATIC FEVER, EXAM 1], [ variable_id=id="phv00000484.v1", name=MF10, description=HISTORY OF ALLERGY OR ASTHMA, EXAM 1], [ variable_id=id="phv00000485.v1", name=MF11, description=HISTORY OF CHRONIC ARTHRITIS AND RHEUMATISM, EXAM 1], [ variable_id=id="phv00000486.v1", name=MF12, description=HISTORY OF THYROID DISEASE, EXAM 1], [ variable_id=id="phv00000487.v1", name=MF13, description=HISTORY OF HYPERTENSION, EXAM 1], [ variable_id=id="phv00000488.v1", name=MF14, description=HISTORY OF ENLARGED HEART, EXAM 1], [ variable_id=id="phv00000489.v1", name=MF15, description=HISTORY OF NERVOUS HEART, EXAM 1], [ variable_id=id="phv00000490.v1", name=MF16, description=HISTORY OF PERICARDITIS, EXAM 1]]
File to use :phs000007.v32.pht000009.v2.ex0_7s.data_dict
File to use :phs000007.v32.pht000009.v2.p13.ex0_7s.var_report
CodeSystem About to ingest
CodeSystem ingested in Fhir 201
Response from  CodeSystem  Ingestion
---------------------------------------------------------------------
{
  "resourceType": "CodeSystem",
  "id": "5003",
  "meta": {
    "versionId": "1",
    "lastUpdated": "2021-02-27T17:34:19.455+00:00"
  },
  "url": "fhir/base/url/Codesystem/pht000009.v2",
  "version": ".v2",
  "name": "ex0_7s",
  "title": "Framingham Cohort",
  "status": "active",
  "experimental": false,
  "publisher": "dbgap",
  "description": "Clinic Exam, Original Cohort Exams 1 - 7",
  "caseSensitive": true,
  "content": "complete",
  "count": 10,
  "concept": [ {
    "code": "phv00000479.v1",
    "display": "MF4",
    "definition": "RELATIVE WEIGHT, EXAM 1"
  }, {
    "code": "phv00000482.v1",
    "display": "MF8",
    "definition": "HISTORY OF ACUTE INFECTIONS, EXAM 1"
  }, {
    "code": "phv00000483.v1",
    "display": "MF9",
    "definition": "HISTORY OF RHEUMATIC FEVER, EXAM 1"
  }, {
    "code": "phv00000484.v1",
    "display": "MF10",
    "definition": "HISTORY OF ALLERGY OR ASTHMA, EXAM 1"
  }, {
    "code": "phv00000485.v1",
    "display": "MF11",
    "definition": "HISTORY OF CHRONIC ARTHRITIS AND RHEUMATISM, EXAM 1"
  }, {
    "code": "phv00000486.v1",
    "display": "MF12",
    "definition": "HISTORY OF THYROID DISEASE, EXAM 1"
  }, {
    "code": "phv00000487.v1",
    "display": "MF13",
    "definition": "HISTORY OF HYPERTENSION, EXAM 1"
  }, {
    "code": "phv00000488.v1",
    "display": "MF14",
    "definition": "HISTORY OF ENLARGED HEART, EXAM 1"
  }, {
    "code": "phv00000489.v1",
    "display": "MF15",
    "definition": "HISTORY OF NERVOUS HEART, EXAM 1"
  }, {
    "code": "phv00000490.v1",
    "display": "MF16",
    "definition": "HISTORY OF PERICARDITIS, EXAM 1"
  } ]
}
---------------------------------------------------------------------
INJEST ValueSet COMPLLETE !!!!!!!!!!!!!!!!!!!
INJEST ConceptMap COMPLLETE!!!!!!!!!!!!
INJEST CodeSystemCOMPLLETE!!!!!!!!!!!!!!!!!!!!!!!!!!!
INJEST COMPLLETE!!!!!!!!!!!! YAY !!!!!!!!!!!!!!!!
 Injest into  Fhir complete
2021-02-27 10:40:10.204  INFO 99659 --- [on(9)-127.0.0.1] inMXBeanRegistrar$SpringApplicationAdmin : Application shutdown requested.
2021-02-27 10:40:10.288  INFO 99659 --- [on(9)-127.0.0.1] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
```

## NLP API Overview
Ultimately, there should be a standard API frontend for performing basic CUI extraction from text blobs such as variable descriptions found in DbGAP's study metadata. However, at this time, the web service we have is coupled with CLAMP, which is not an open source product. Resarchers wishing to try this out must get permission from the team developing CLAMP to use our web service. Details about how to get said permission to follow (TODO: get details about inquiring about CLAMP).

### NLP API 
There are one function: getJson which can be called via GET or POST with a single parameter, Text. 

#### getJson
This passes the contents of the Text parameter to CLAMP and returns a JSON structure with the results.

Parameters:
> text  - This is the text to be parsed by the NLP backend

``` 
input

http://localhost:8080/getJson?text=RELATIVE WEIGHT,EXAM 1
```
``` 
output

{
    "Results": [
        {
            "Location_Start": 0,
            "Location_End": 10,
            "Semantics": "problem",
            "CUI": "C1542178,SNOMEDCT_US[414292006,46866001]",
            "Assertion": "present",
            "Entity": "broken leg",
            "Concept_Prob": 0.76
        },
        {
            "Location_Start": 7,
            "Location_End": 10,
            "Semantics": "BDL",
            "CUI": null,
            "Assertion": null,
            "Entity": "leg"
        }
    ]
}
```