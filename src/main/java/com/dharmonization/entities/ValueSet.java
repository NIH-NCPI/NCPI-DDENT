package com.dharmonization.entities;

public class ValueSet {
	
	public String resourceType;
    public String id;
    public String url;
    public String version;
    public String name;
    public String title;
    public String status;
    public boolean experimental;
    public String publisher;
    public String description;
    public Compose compose;
    
    
    
	

	public ValueSet(String resourceType, String id, String url, String version, String name, String title,
			String status, boolean experimental, String publisher, String description, Compose compose
			) {
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
		this.compose = compose;
		
	}

	public ValueSet() {
		super();
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

	public Compose getCompose() {
		return compose;
	}

	public void setCompose(Compose compose) {
		this.compose = compose;
	}



	@Override
	public String toString() {
		return "ValueSet [resourceType=" + resourceType + ", id=" + id + ", url=" + url + ", version=" + version
				+ ", name=" + name + ", title=" + title + ", status=" + status + ", experimental=" + experimental
				+ ", publisher=" + publisher + ", description=" + description + ", compose=" + compose + ","+
				 "]";
	}

	
    

}
