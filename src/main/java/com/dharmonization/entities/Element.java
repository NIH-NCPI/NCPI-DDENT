package com.dharmonization.entities;

import java.util.List;

public class Element {

	public String code;
	public String display;
    public List<Target> target;
    
	public Element() {
		super();
	}

	public Element(String code, String display, List<Target> target) {
		super();
		this.code = code;
		this.display = display;
		this.target = target;
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

	public List<Target> getTarget() {
		return target;
	}

	public void setTarget(List<Target> target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "Element [code=" + code + ", display=" + display + ", target=" + target + "]";
	}
    
	
    
}
