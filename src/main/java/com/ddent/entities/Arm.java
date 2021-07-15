package com.ddent.entities;

public class Arm {

	public String name;
    public String type;
    public String description;
    
    
	public Arm() {
		super();
	}


	public Arm(String name, String type, String description) {
		super();
		this.name = name;
		this.type = type;
		this.description = description;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Arm [name=" + name + ", type=" + type + ", description=" + description + "]";
	}
    
	
    
}
