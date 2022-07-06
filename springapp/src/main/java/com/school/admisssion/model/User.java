package com.school.admisssion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int userid;
	private String user_type;
	@Column(unique = true)
	private String email;
	private String name;
	private String mobno;
	private String pwd;
	private String confirmpwd;
	
	
	
	public User(int userid, String user_type, String email, String name, String mobno, String pwd, String confirmpwd) {
		this.userid = userid;
		this.user_type = user_type;
		this.email = email;
		this.name = name;
		this.mobno = mobno;
		this.pwd = pwd;
		this.confirmpwd = confirmpwd;
	}

	public User() {
		
	}
	
	@Override
	public String toString() {
		return "User [confirmpwd=" + confirmpwd + ", email=" + email + ", mobno=" + mobno + ", name=" + name + ", pwd="
				+ pwd + ", user_type=" + user_type + ", userid=" + userid + "]";
	}

	public String getConfirmpwd() {
		return confirmpwd;
	}

	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}
	
	
	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobno() {
		return mobno;
	}
	public void setMobno(String mobno) {
		this.mobno = mobno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
	

}
