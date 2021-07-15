package com.ddent.entities;

import java.util.List;

public class CodeSystem {
	
	private String resourceType;
	
	private String id;
	
	private String url;
	
	private String version;
	
	private String name;
	
	private String title;
	
	private String status;
	
	private boolean experimental;
	
	private String publisher;
	
	private String description;
	
	private boolean caseSensitive;
	
	private String content;
	
	private int count;
	
	private List<Concept> concept;

	public CodeSystem() {
		super();
	}

	public CodeSystem(String resourceType, String id, String url, String version, String name, String title,
			String status, boolean experimental, String publisher, String description, boolean caseSensitive,
			String content, int count, List<Concept> concept) {
		super();
		this.resourceType = resourceType;
		this.id = id;
		this.url = url;
		this.version = version;
		this.name = name;
		this.title = title;
		this.status = status;
		this.experimental = experimental;
		this.publisher = publisher;
		this.description = description;
		this.caseSensitive = caseSensitive;
		this.content = content;
		this.count = count;
		this.concept = concept;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Concept> getConcept() {
		return concept;
	}

	public void setConcept(List<Concept> concept) {
		this.concept = concept;
	}

	@Override
	public String toString() {
		return "CodeSystem [resourceType=" + resourceType + ", id=" + id + ", url=" + url + ", version=" + version
				+ ", name=" + name + ", title=" + title + ", status=" + status + ", experimental=" + experimental
				+ ", publisher=" + publisher + ", description=" + description + ", caseSensitive=" + caseSensitive
				+ ", content=" + content + ", count=" + count + ", concept=" + concept + "]";
	}


	
	
	
	
}
