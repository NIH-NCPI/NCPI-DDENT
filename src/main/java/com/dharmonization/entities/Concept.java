package com.dharmonization.entities;

public class Concept {

	private String code;
	
	private String display;
	
	private String definition;

	
	
	public Concept() {
		super();
	}



	public Concept(String code, String display, String definition) {
		super();
		this.code = code;
		this.display = display;
		this.definition = definition;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getDisplay() {
		return display;
	}



	public void setDisplay(String display) {
		this.display = display;
	}



	public String getDefinition() {
		return definition;
	}



	public void setDefinition(String definition) {
		this.definition = definition;
	}



	@Override
	public String toString() {
		return "Concept [code=" + code + ", display=" + display + ", definition=" + definition + "]";
	}

	






	
	
	
	
}
