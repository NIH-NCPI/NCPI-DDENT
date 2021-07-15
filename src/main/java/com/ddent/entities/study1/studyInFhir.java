package com.ddent.entities.study1;

import java.util.List;

import javax.persistence.Entity;

import com.ddent.entities.Arm;
import com.ddent.entities.Objective;

@Entity
public class studyInFhir {

	public String resourceType;
    public String identifier;
    public String title;
    public List<Object> protocol;
    public List<Object> partOf;
    public String status;
    public String primaryPurposeType;
    public String phase;
    public List<Object> category;
    public List<Object> focus;
    public List<Object> condition;
    public List<Object> contact;
    public List<Object> relatedArtifact;
    public List<Object> keyword;
    public List<Object> location;
    public String description;
    public String enrollment;
    public String period;
    public String sponsor;
    public String principalInvestigator;
    public List<Object> site;
    public String reasonStopped;
    public List<Object> note;
    public List<Arm> arm;
    public List<Objective> objective;
    
    
	public studyInFhir() {
		super();
	}


	public studyInFhir(String resourceType, String identifier, String title, List<Object> protocol,
			List<Object> partOf, String status, String primaryPurposeType, String phase, List<Object> category,
			List<Object> focus, List<Object> condition, List<Object> contact, List<Object> relatedArtifact,
			List<Object> keyword, List<Object> location, String description, String enrollment, String period,
			String sponsor, String principalInvestigator, List<Object> site, String reasonStopped, List<Object> note,
			List<Arm> arm, List<Objective> objective) {
		super();
		this.resourceType = resourceType;
		this.identifier = identifier;
		this.title = title;
		this.protocol = protocol;
		this.partOf = partOf;
		this.status = status;
		this.primaryPurposeType = primaryPurposeType;
		this.phase = phase;
		this.category = category;
		this.focus = focus;
		this.condition = condition;
		this.contact = contact;
		this.relatedArtifact = relatedArtifact;
		this.keyword = keyword;
		this.location = location;
		this.description = description;
		this.enrollment = enrollment;
		this.period = period;
		this.sponsor = sponsor;
		this.principalInvestigator = principalInvestigator;
		this.site = site;
		this.reasonStopped = reasonStopped;
		this.note = note;
		this.arm = arm;
		this.objective = objective;
	}


	public String getResourceType() {
		return resourceType;
	}


	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}


	public String getIdentifier() {
		return identifier;
	}


	public void setIdentifier(String string) {
		this.identifier = string;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public List<Object> getProtocol() {
		return protocol;
	}


	public void setProtocol(List<Object> protocol) {
		this.protocol = protocol;
	}


	public List<Object> getPartOf() {
		return partOf;
	}


	public void setPartOf(List<Object> partOf) {
		this.partOf = partOf;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPrimaryPurposeType() {
		return primaryPurposeType;
	}


	public void setPrimaryPurposeType(String primaryPurposeType) {
		this.primaryPurposeType = primaryPurposeType;
	}


	public String getPhase() {
		return phase;
	}


	public void setPhase(String phase) {
		this.phase = phase;
	}


	public List<Object> getCategory() {
		return category;
	}


	public void setCategory(List<Object> category) {
		this.category = category;
	}


	public List<Object> getFocus() {
		return focus;
	}


	public void setFocus(List<Object> focus) {
		this.focus = focus;
	}


	public List<Object> getCondition() {
		return condition;
	}


	public void setCondition(List<Object> condition) {
		this.condition = condition;
	}


	public List<Object> getContact() {
		return contact;
	}


	public void setContact(List<Object> contact) {
		this.contact = contact;
	}


	public List<Object> getRelatedArtifact() {
		return relatedArtifact;
	}


	public void setRelatedArtifact(List<Object> relatedArtifact) {
		this.relatedArtifact = relatedArtifact;
	}


	public List<Object> getKeyword() {
		return keyword;
	}


	public void setKeyword(List<Object> keyword) {
		this.keyword = keyword;
	}


	public List<Object> getLocation() {
		return location;
	}


	public void setLocation(List<Object> location) {
		this.location = location;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getEnrollment() {
		return enrollment;
	}


	public void setEnrollment(String string) {
		this.enrollment = string;
	}


	public String getPeriod() {
		return period;
	}


	public void setPeriod(String period) {
		this.period = period;
	}


	public String getSponsor() {
		return sponsor;
	}


	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}


	public String getPrincipalInvestigator() {
		return principalInvestigator;
	}


	public void setPrincipalInvestigator(String principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}


	public List<Object> getSite() {
		return site;
	}


	public void setSite(List<Object> site) {
		this.site = site;
	}


	public String getReasonStopped() {
		return reasonStopped;
	}


	public void setReasonStopped(String reasonStopped) {
		this.reasonStopped = reasonStopped;
	}


	public List<Object> getNote() {
		return note;
	}


	public void setNote(List<Object> note) {
		this.note = note;
	}


	public List<Arm> getArm() {
		return arm;
	}


	public void setArm(List<Arm> arm) {
		this.arm = arm;
	}


	public List<Objective> getObjective() {
		return objective;
	}


	public void setObjective(List<Objective> objective) {
		this.objective = objective;
	}


	@Override
	public String toString() {
		return "studyInFhir [resourceType=" + resourceType + ", identifier=" + identifier + ", title=" + title
				+ ", protocol=" + protocol + ", partOf=" + partOf + ", status=" + status + ", primaryPurposeType="
				+ primaryPurposeType + ", phase=" + phase + ", category=" + category + ", focus=" + focus
				+ ", condition=" + condition + ", contact=" + contact + ", relatedArtifact=" + relatedArtifact
				+ ", keyword=" + keyword + ", location=" + location + ", description=" + description + ", enrollment="
				+ enrollment + ", period=" + period + ", sponsor=" + sponsor + ", principalInvestigator="
				+ principalInvestigator + ", site=" + site + ", reasonStopped=" + reasonStopped + ", note=" + note
				+ ", arm=" + arm + ", objective=" + objective + "]";
	}
	
	
    
    
	
}
