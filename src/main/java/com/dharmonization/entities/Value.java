package com.dharmonization.entities;



import javax.persistence.Entity;



@Entity
public class Value {
	
	
	private String code;

    private String content;
    
    
    
    
    
    
    public Value() {
		super();
	}
    
    public Value(String code, String content) {
		super();
		this.code = code;
		this.content = content;
	}

    
    
    
    
	public String getCode() {
		return code;
	}


	public String getContent() {
		return content;
	}







	



	

}

