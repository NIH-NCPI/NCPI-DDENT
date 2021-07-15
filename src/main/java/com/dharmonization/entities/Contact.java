package com.dharmonization.entities;

import java.util.List;

public class Contact {

	public String name;
    public List<Telecom> telecom;
	public Contact(String name, List<Telecom> telecom) {
		super();
		this.name = name;
		this.telecom = telecom;
	}
	public Contact() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Telecom> getTelecom() {
		return telecom;
	}
	public void setTelecom(List<Telecom> telecom) {
		this.telecom = telecom;
	}
	@Override
	public String toString() {
		return "Contact [name=" + name + ", telecom=" + telecom + "]";
	}
    
}
