package com.ddent.entities;

import java.util.List;

public class Compose {

	 public List<Include> include;

	public Compose(List<Include> include) {
		super();
		this.include = include;
	}

	public Compose() {
		super();
	}

	public List<Include> getInclude() {
		return include;
	}

	public void setInclude(List<Include> include) {
		this.include = include;
	}

	@Override
	public String toString() {
		return "Compose [include=" + include + "]";
	}
	 
	 
}
