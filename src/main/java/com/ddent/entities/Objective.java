package com.ddent.entities;

public class Objective {

	public String name;
    public String type;
    
	public Objective() {
		super();
	}

	public Objective(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Objective [name=" + name + ", type=" + type + "]";
	}
    
    
}
