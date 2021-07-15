package com.dharmonization.entities;

import javax.persistence.Entity;



@Entity
public class Variable {
	
	
	private String id;
	
	
	private String name;
	
	private String description;
	
	private String unit;
	
	private Values value;
	
	
	

	public Variable() {
		super();
	}

	public Variable(String id, String name, String description, String unit, Values value) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.unit = unit;
		this.value = value;
	}
	
	
	
	

	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public String getUnit() {
		return unit;
	}


	public Values getValue() {
		return value;
	}



}
