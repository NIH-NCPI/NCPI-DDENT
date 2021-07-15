package com.ddent.entities;

public class Target {

	
	    public String code;
	    public String equivalence;
	    public String comment;
	    
		public Target(String code, String equivalence, String comment) {
			super();
			this.code = code;
			this.equivalence = equivalence;
			this.comment = comment;
		}
		public Target() {
			super();
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getEquivalence() {
			return equivalence;
		}
		public void setEquivalence(String equivalence) {
			this.equivalence = equivalence;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		@Override
		public String toString() {
			return "Target [code=" + code + ", equivalence=" + equivalence + ", comment=" + comment + "]";
		}
	    
	    
}
