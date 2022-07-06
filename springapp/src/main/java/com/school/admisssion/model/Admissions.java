package com.school.admisssion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admissions {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    private int admissionid;
    private int regid;
    private String username;
    private String useremail;
	private String title;
	private String instituteName;
	private String academicYear;
    private String admissionstatus;
    private String adminName;

    public int getRegid() {
        return regid;
    }

    public void setRegid(int regid) {
        this.regid = regid;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Admissions() {
    }
    
    public int getAdmissionid() {
        return admissionid;
    }
    public void setAdmissionid(int admissionid) {
        this.admissionid = admissionid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUseremail() {
        return useremail;
    }
    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public String getAdmissionstatus() {
        return admissionstatus;
    }
    public void setAdmissionstatus(String admissionstatus) {
        this.admissionstatus = admissionstatus;
    }

    @Override
    public String toString() {
        return "Admissions [academicYear=" + academicYear + ", adminName=" + adminName + ", admissionid=" + admissionid
                + ", admissionstatus=" + admissionstatus + ", instituteName=" + instituteName + ", regid=" + regid
                + ", title=" + title + ", useremail=" + useremail + ", username=" + username + "]";
    }

    

   
    

    
}
