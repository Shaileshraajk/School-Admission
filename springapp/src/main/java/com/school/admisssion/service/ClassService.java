package com.school.admisssion.service;

import java.util.List;

import com.school.admisssion.model.ClassRegistration;
import com.school.admisssion.model.Classes;

public interface ClassService {
	
	public List<Classes> getAllClasses();

	public List<ClassRegistration> getAllRegisteredClasses();
	
	public List<Classes> getClassbyClassno(String classno);
	
	public boolean CheckifAlreadyRegistered(String useremail, String classno);
	
	public ClassRegistration enrollClass(ClassRegistration crg);


	public ClassRegistration classRegistrationDetails(String useremail, String classno);

	public ClassRegistration classRegistrationDetails(int regid);
	
	public List<ClassRegistration> viewenrolled(String email);

	public List<ClassRegistration> getClassRegDetails(String classno);

	public ClassRegistration saveRegistration(ClassRegistration crgs);
	
	public int deleteenrolled(int regid, String useremail);

	public Classes addClass(Classes crs);

	public Classes classDetails(int id);

	public Classes saveClass(Classes crs);

	public int deletebyId(int classid);

	public int deletefromClassReg(String classno);

	public void deleteAll();


}
