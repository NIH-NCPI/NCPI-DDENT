package com.dharmonization.entities;

import java.util.List;

public class Include {

	 private String system;
	 
	 public List<ValuesetConcept> concept;

	public Include() {
		super();
	}

	public Include(String system, List<ValuesetConcept> concept) {
		super();
		this.system = system;
		this.concept = concept;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public List<ValuesetConcept> getConcept() {
		return concept;
	}

	public void setConcept(List<ValuesetConcept> concept) {
		this.concept = concept;
	}

	@Override
	public String toString() {
		return "Include [system=" + system + ", concept=" + concept + "]";
	}

	
	 
	 
}
