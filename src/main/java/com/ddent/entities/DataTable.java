package com.ddent.entities;



import java.util.List;

import javax.persistence.Entity;


import com.ddent.entities.study1.studyPlusNlp;



@Entity
public class DataTable {
	
	
private String study_id;
	
	private String table_id;
	
	private String study_description;
	
	private String date_created;
	
	private String participant_set;
	

	private List <studyPlusNlp> ListOfStudyPlusNlp;


	
	public DataTable() {
		super();
	}



	public DataTable(String study_id, String table_id, String study_description, String date_created,
			String participant_set, List<studyPlusNlp> listOfStudyPlusNlp) {
		super();
		this.study_id = study_id;
		this.table_id = table_id;
		this.study_description = study_description;
		this.date_created = date_created;
		this.participant_set = participant_set;
		ListOfStudyPlusNlp = listOfStudyPlusNlp;
	}



	public String getStudy_id() {
		return study_id;
	}



	public void setStudy_id(String study_id) {
		this.study_id = study_id;
	}



	public String getTable_id() {
		return table_id;
	}



	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}



	public String getStudy_description() {
		return study_description;
	}



	public void setStudy_description(String study_description) {
		this.study_description = study_description;
	}



	public String getDate_created() {
		return date_created;
	}



	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}



	public String getParticipant_set() {
		return participant_set;
	}



	public void setParticipant_set(String participant_set) {
		this.participant_set = participant_set;
	}



	public List<studyPlusNlp> getListOfStudyPlusNlp() {
		return ListOfStudyPlusNlp;
	}



	public void setListOfStudyPlusNlp(List<studyPlusNlp> listOfStudyPlusNlp) {
		ListOfStudyPlusNlp = listOfStudyPlusNlp;
	}



	@Override
	public String toString() {
		return "DataTable [study_id=" + study_id + ", table_id=" + table_id + ", study_description=" + study_description
				+ ", date_created=" + date_created + ", participant_set=" + participant_set + ", ListOfStudyPlusNlp="
				+ ListOfStudyPlusNlp + "]";
	}
	
	

}
