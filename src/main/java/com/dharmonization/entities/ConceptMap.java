package com.dharmonization.entities;

import java.util.List;

public class ConceptMap {

	public String resourceType;
	public Text text;
    public String id;
    public String url;
    public Identifier identifier;
    public String version;
    //public String name;
    public String status;
    public boolean experimental;
    //public String date;
    public String publisher;
    public List<Contact> contact;
    public String description;
    public String purpose;
    public String sourceCanonical;
    public String targetCanonical;
    public List<Group> group;
    
	public ConceptMap() {
		super();
	}

	public ConceptMap(String resourceType, Text text, String id, String url, Identifier identifier, String version,
			String status, boolean experimental, String publisher, List<Contact> contact, String description,
			String purpose, String sourceCanonical, String targetCanonical, List<Group> group) {
		super();
		this.resourceType = resourceType;
		this.text = text;
		this.id = id;
		this.url = url;
		this.identifier = identifier;
		this.version = version;
		this.status = status;
		this.experimental = experimental;
		this.publisher = publisher;
		this.contact = contact;
		this.description = description;
		this.purpose = purpose;
		this.sourceCanonical = sourceCanonical;
		this.targetCanonical = targetCanonical;
		this.group = group;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isExperimental() {
		return experimental;
	}

	public void setExperimental(boolean experimental) {
		this.experimental = experimental;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<Contact> getContact() {
		return contact;
	}

	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getSourceCanonical() {
		return sourceCanonical;
	}

	public void setSourceCanonical(String sourceCanonical) {
		this.sourceCanonical = sourceCanonical;
	}

	public String getTargetCanonical() {
		return targetCanonical;
	}

	public void setTargetCanonical(String targetCanonical) {
		this.targetCanonical = targetCanonical;
	}

	public List<Group> getGroup() {
		return group;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "ConceptMap [resourceType=" + resourceType + ", text=" + text + ", id=" + id + ", url=" + url
				+ ", identifier=" + identifier + ", version=" + version + ", status=" + status + ", experimental="
				+ experimental + ", publisher=" + publisher + ", contact=" + contact + ", description=" + description
				+ ", purpose=" + purpose + ", sourceCanonical=" + sourceCanonical + ", targetCanonical="
				+ targetCanonical + ", group=" + group + "]";
	}
    
	
    
}
