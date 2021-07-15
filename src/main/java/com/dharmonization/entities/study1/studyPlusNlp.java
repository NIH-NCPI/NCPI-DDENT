package com.dharmonization.entities.study1;

import java.util.List;

import javax.persistence.Entity;


@Entity
public class studyPlusNlp {


	
	@Override
	public String toString() {
		return "studyPlusNlp [variable_id=" + variable_id + ", name=" + name + ", description=" + description + ", Nlp="
				+ Nlp + "]";
	}



	private String variable_id;
	
	private String name;
	
	private String description;
	
	private List <nlp>  Nlp;

	
	
	public studyPlusNlp() {
		super();
	}


	



	public studyPlusNlp(String variable_id, String name, String description, List<nlp> nlp) {
		super();
		this.variable_id = variable_id;
		this.name = name;
		this.description = description;
		Nlp = nlp;
	}






	public List<nlp> getNlp() {
		return Nlp;
	}








	public void setNlp(List<nlp> nlp) {
		Nlp = nlp;
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



	
}
