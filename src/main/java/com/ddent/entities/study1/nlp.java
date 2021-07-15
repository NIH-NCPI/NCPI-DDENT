package com.ddent.entities.study1;

import javax.persistence.Entity;

@Entity
public class nlp {

	private String location_start;
	
	private String location_end;
	
	private String semantics;
	
	private String cui;
	
	private String assertiion;
	
	private String entity;
	
	private String Concept_prob;

	
	
	
	public nlp() {
		super();
	}




	public nlp(String location_start, String location_end, String semantics, String cui, String assertiion,
			String entity, String concept_prob) {
		super();
		this.location_start = location_start;
		this.location_end = location_end;
		this.semantics = semantics;
		this.cui = cui;
		this.assertiion = assertiion;
		this.entity = entity;
		Concept_prob = concept_prob;
	}




	public String getLocation_start() {
		return location_start;
	}




	public void setLocation_start(String location_start) {
		this.location_start = location_start;
	}




	public String getLocation_end() {
		return location_end;
	}




	public void setLocation_end(String location_end) {
		this.location_end = location_end;
	}




	public String getSemantics() {
		return semantics;
	}




	public void setSemantics(String semantics) {
		this.semantics = semantics;
	}




	public String getCui() {
		return cui;
	}




	public void setCui(String cui) {
		this.cui = cui;
	}




	public String getAssertiion() {
		return assertiion;
	}




	public void setAssertiion(String assertiion) {
		this.assertiion = assertiion;
	}




	public String getEntity() {
		return entity;
	}




	public void setEntity(String entity) {
		this.entity = entity;
	}




	public String getConcept_prob() {
		return Concept_prob;
	}




	public void setConcept_prob(String concept_prob) {
		Concept_prob = concept_prob;
	}




	@Override
	public String toString() {
		return "nlp [location_start=" + location_start + ", location_end=" + location_end + ", semantics=" + semantics
				+ ", cui=" + cui + ", assertiion=" + assertiion + ", entity=" + entity + ", Concept_prob="
				+ Concept_prob + "]";
	}



	



	
	
	
}
