package com.dharmonization.entities;

public class Text {
	
	public String status;
    public String div;
    
    
	public Text() {
		super();
	}


	public Text(String status, String div) {
		super();
		this.status = status;
		this.div = div;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDiv() {
		return div;
	}


	public void setDiv(String div) {
		this.div = div;
	}


	@Override
	public String toString() {
		return "Text [status=" + status + ", div=" + div + "]";
	}
    
    

}
