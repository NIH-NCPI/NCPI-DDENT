package com.dharmonization.entities.study1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;


@Entity
public class nlps {
	
	private List<nlp> Nlp = new ArrayList<>();
	
	

	public nlps() {
		super();
	}



	public nlps(List<nlp> nlp) {
		super();
		Nlp = nlp;
	}



	public List<nlp> getNlp() {
		return Nlp;
	}



	public void setNlp(List<nlp> nlp) {
		Nlp = nlp;
	}



	@Override
	public String toString() {
		return "nlps [Nlp=" + Nlp + "]";
	}
	
	

}
