package com.school.admisssion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClassRegistration {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int regid;
	private String useremail;
	private String username;
	private float scoredmarks;
	private String classno;
	private String instituteName;
	private String academicYear;
	private int eligibleMarks;
	private String Marksfile;

	public ClassRegistration() {
	}

	public int getRegid() {
		return regid;
	}

	public void setRegid(int regid) {
		this.regid = regid;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public float getScoredmarks() {
		return scoredmarks;
	}

	public void setScoredmarks(float scoredmarks) {
		this.scoredmarks = scoredmarks;
	}

	public String getClassno() {
		return classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public int getEligibleMarks() {
		return eligibleMarks;
	}

	public void setEligibleMarks(int eligibleMarks) {
		this.eligibleMarks = eligibleMarks;
	}

	public String getMarksfile() {
		return Marksfile;
	}

	public void setMarksfile(String marksfile) {
		Marksfile = marksfile;
	}

	@Override
	public String toString() {
		return "ClassRegistration [Marksfile=" + Marksfile + ", academicYear=" + academicYear + ", classno=" + classno
				+ ", eligibleMarks=" + eligibleMarks + ", instituteName=" + instituteName + ", regid=" + regid
				+ ", scoredmarks=" + scoredmarks + ", useremail=" + useremail + ", username=" + username + "]";
	}

	

	

	
	
	
	

	

	
	
	
	


	
	
	
	

}
