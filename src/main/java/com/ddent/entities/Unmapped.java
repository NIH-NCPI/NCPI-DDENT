package com.ddent.entities;

public class Unmapped {

	public String mode;
    public String code;
    public String display;
    
	public Unmapped() {
		super();
	}

	public Unmapped(String mode, String code, String display) {
		super();
		this.mode = mode;
		this.code = code;
		this.display = display;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Override
	public String toString() {
		return "Unmapped [mode=" + mode + ", code=" + code + ", display=" + display + "]";
	}
    
    
    
}
