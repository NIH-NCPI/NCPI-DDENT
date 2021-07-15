package com.dharmonization.entities;

public class Identifier {

	public String system;
    public String value;
	public Identifier(String system, String value) {
		super();
		this.system = system;
		this.value = value;
	}
	public Identifier() {
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
		return "Identifier [system=" + system + ", value=" + value + "]";
	}
    
    
}
