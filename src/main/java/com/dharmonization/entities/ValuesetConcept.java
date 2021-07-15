package com.dharmonization.entities;

public class ValuesetConcept {
	private String code;
	
	private String display;

	public ValuesetConcept() {
		super();
	}

	public ValuesetConcept(String code, String display) {
		super();
		this.code = code;
		this.display = display;
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

	@Override
	public String toString() {
		return "ValuesetConcept [code=" + code + ", display=" + display + "]";
	}
	

	
	
}

