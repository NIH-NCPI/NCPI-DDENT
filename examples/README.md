# Example JSON files for NLP Based Data Dictionary

For this example, we'll assume that we have three dbgap variables:
-	Study: phs001.v1
-	Table: pht001.v1

## Variables: 
- phv001.v1	has asthma
- phv002.v1 	problem with wheezing
- phv003.v1 	RELATIVE WEIGHT

When the descriptions above are processed by NLP module, these map to the following CUI respectively:
- C0004096,SNOMEDCT_US[195967001]
- C0043144,SNOMEDCT_US[56018004]
- C1262477

We will create 1 FHIR CodeSystem to represent the variables from the first block above (pht001-CodeSystem.json, pht001-ValueSet.json). I believe you will create a single code system for each Study/Table unless those variable IDs are scoped more broadly. 
We will create 1 FHIR CodeSystem to represent all cuis across all tables (we are assuming that this will be run across many DbGAP studies/tables)

From each of the two code systems above, we must also create a ValueSet. So there are two value sets. 

Finally, to map concepts from one system (dbgap) to another (cui), we'll create a ConceptMap. At the very least, we'll have 1 ConceptMap per study, but it may be that there must be a 1 to 1 relationship between table and conceptmap.

For first pass, I recommend just mapping CUI to both code and display in your CUI CodeSystem/ValueSets. However, I imagine the ultimate goal will be to actually add in understanding of what those concepts really are so that you can maintain global CodeSystems (1 for SNOMED, one for NCBI concepts, etc). So, rather than pointing to C0004096,SNOMEDCT_asdfasdf you would build valuesets with the corresponding system information. We can discuss that at a later date.

It's also worth learning whether there is interest in mapping things like COUNTRY OF BIRTH to something like https://loinc.org/LL197-5/  Right now, those highly simplistic entries appear to fail, but you could probably build up some simple matchers for them. 


## Details that need serious attention
### Equivalence
I don't know if there is any way to get this information from the NLP, but if this field is required and has a limited vocabulary which does have clear meaning: relatedto | equivalent | equal | wider | subsumes | narrower | specializes | inexact | unmatched | disjoint

Equal or equivalent may not be correct?

### Can multiple codes go inside the target?
One would think so, based on the cardinality shown at HL7s resource page, but I never saw it in any of the examples I looked at. I did see one where there were two target objects which seems weird. 

### Product probably isn't relevant...maybe?
I would take some time to learn what target.product really is, because it may be important and the description on the resource page isn't particularly clear to me. 

### Unmapped
Yeah, this may be necessary to use for all of those things that the NLP fails to find a proper match for. 


