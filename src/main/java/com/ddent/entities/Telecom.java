package com.ddent.entities;

public class Telecom {
	
	public String system;
    public String value;
	public Telecom(String system, String value) {
		super();
		this.system = system;
		this.value = value;
	}
	public Telecom() {
		super();
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Telecom [system=" + system + ", value=" + value + "]";
	}
    

}
