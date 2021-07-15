package com.dharmonization.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Variables {
	
	
	private List<Variable> variables = new ArrayList<>();

	
	public Variables() {
		super();
	}


	public List<Variable> getVariables() {
		return variables;
	}


}
