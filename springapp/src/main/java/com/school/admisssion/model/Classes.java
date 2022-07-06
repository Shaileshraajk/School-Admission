package com.school.admisssion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Classes {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int classid;
	private String classno;
	private int instituteid;
	private String institute_name;
	private String academicYear;
	private int elgibleMarks;
	
	public Classes() {
	}

	public int getClassid() {
		return classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public String getClassno() {
		return classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	public int getInstituteid() {
		return instituteid;
	}

	public void setInstituteid(int instituteid) {
		this.instituteid = instituteid;
	}

	public String getInstitute_name() {
		return institute_name;
	}

	public void setInstitute_name(String institute_name) {
		this.institute_name = institute_name;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public int getElgibleMarks() {
		return elgibleMarks;
	}

	public void setElgibleMarks(int elgibleMarks) {
		this.elgibleMarks = elgibleMarks;
	}

	@Override
	public String toString() {
		return "Classes [academicYear=" + academicYear + ", classid=" + classid + ", classno=" + classno
				+ ", elgibleMarks=" + elgibleMarks + ", institute_name=" + institute_name + ", instituteid="
				+ instituteid + "]";
	}

	

	

	


	

	
	
	
	

}
