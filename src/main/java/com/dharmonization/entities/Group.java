package com.dharmonization.entities;

import java.util.List;

public class Group {

	public String source;
    public String sourceVersion;
    public String target;
    public String targetVersion;
    public List<Element> element;
    //public List<Unmapped>  unmapped;
    
	public Group() {
		super();
	}

	public Group(String source, String sourceVersion, String target, String targetVersion, List<Element> element
			) {
		super();
		this.source = source;
		this.sourceVersion = sourceVersion;
		this.target = target;
		this.targetVersion = targetVersion;
		this.element = element;
		//this.unmapped = unmapped;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceVersion() {
		return sourceVersion;
	}

	public void setSourceVersion(String sourceVersion) {
		this.sourceVersion = sourceVersion;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTargetVersion() {
		return targetVersion;
	}

	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}

	public List<Element> getElement() {
		return element;
	}

	public void setElement(List<Element> element) {
		this.element = element;
	}

//	public List<Unmapped> getUnmapped() {
//		return unmapped;
//	}
//
//	public void setUnmapped(List<Unmapped> unmapped) {
//		this.unmapped = unmapped;
//	}

	@Override
	public String toString() {
		return "Group [source=" + source + ", sourceVersion=" + sourceVersion + ", target=" + target
				+ ", targetVersion=" + targetVersion + ", element=" + element + "]";
	}
    
	
    
}
