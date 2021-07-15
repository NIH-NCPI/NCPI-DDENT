package com.ddent.entities.study1;

import javax.persistence.Entity;

@Entity
public class study {

	
	private String variable_id;
	
	private String name;
	
	private String description;
	
	

	public study() {
		super();
	}



	public study(String variable_id, String name, String description) {
		super();
		this.variable_id = variable_id;
		this.name = name;
		this.description = description;
	}
	
	





	public String getVariable_id() {
		return variable_id;
	}



	public void setVariable_id(String variable_id) {
		this.variable_id = variable_id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



//	@Override
//	public String toString() {
//		return "study [study_id=" + study_id + ", table_id=" + table_id + ", study_description=" + study_description
//				+ ", variable_id=" + variable_id + ", name=" + name + ", description=" + description + "]";
//	}
	@Override
	public String toString() {
		return "[ variable_id=" + variable_id + ", name=" + name + ", description=" + description + "]";
	}
	
	
	
}
