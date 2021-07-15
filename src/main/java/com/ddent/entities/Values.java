package com.ddent.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;


@Entity
public class Values {
	
	
	private List<Value> values = new ArrayList<>();
	
	
	

	public Values() {
		super();
	}
	
	

	public List<Value> getValues() {
		return values;
	}
	

}
