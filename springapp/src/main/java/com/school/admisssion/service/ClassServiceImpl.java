package com.school.admisssion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.admisssion.model.ClassRegistration;
import com.school.admisssion.model.Classes;
import com.school.admisssion.repository.ClassRegRepo;
import com.school.admisssion.repository.ClassRepo;

@Service
public class ClassServiceImpl implements ClassService {
	
	
	@Autowired
	private ClassRepo classrepo;
	
	@Autowired
	private ClassRegRepo crgrepo;


	@Override
	public List<Classes> getClassbyClassno(String classno) {
		return classrepo.findByClassnoContaining(classno);
	}


	@Override
	public boolean CheckifAlreadyRegistered(String useremail, String classno) {
		return crgrepo.findByUseremailAndClassno(useremail, classno)!=null ? true:false;
	}


	@Override
	public ClassRegistration enrollClass(ClassRegistration crg) {
		if(CheckifAlreadyRegistered(crg.getUseremail(),crg.getClassno())){
			return null;
		}
		return crgrepo.save(crg);
	}

	@Override
	public List<ClassRegistration> viewenrolled(String email) {
		return crgrepo.findByUseremail(email);
	}

	@Override
	public int deleteenrolled(int regid, String useremail) {
		return crgrepo.deleteByRegidAndUseremail(regid, useremail);
	}

	@Override
	public Classes addClass(Classes crs) {
		if(ClassAlreadyExits(crs.getClassno(), crs.getInstituteid())){
			return null;
		}
		return classrepo.save(crs);
	}

	private boolean ClassAlreadyExits(String classno, int instituteid){
		if(classrepo.findByClassnoAndInstituteid(classno, instituteid)!=null){
			return true;
		}
		return false;
	}

	@Override
	public Classes classDetails(int id) {
		if(classrepo.findByClassid(id)!=null){
			return classrepo.findByClassid(id);
		}
		return null;
	}


	@Override
	public Classes saveClass(Classes crs) {
		return classrepo.save(crs);
	}

	@Override
	public int deletebyId(int classid) {
		return classrepo.deleteByClassid(classid);
	}

	@Override
	public void deleteAll() {
		classrepo.deleteAll();
	}

	
	@Override
	public ClassRegistration classRegistrationDetails(String useremail, String classno) {
		if(crgrepo.findByUseremailAndClassno(useremail, classno)!=null){
			return crgrepo.findByUseremailAndClassno(useremail, classno);
		}
		return null;
	}

	@Override
	public ClassRegistration classRegistrationDetails(int regid) {
		if(crgrepo.findByRegid(regid)!=null){
			return crgrepo.findByRegid(regid);
		}
		return null;
	}


	@Override
	public int deletefromClassReg(String classno) {
		return crgrepo.deleteByClassno(classno);
	}

	@Override
	public List<ClassRegistration> getClassRegDetails(String classno) {
		return crgrepo.findByClassno(classno);
	}

	@Override
	public ClassRegistration saveRegistration(ClassRegistration crgs) {
		return crgrepo.save(crgs);
	}


	@Override
	public List<ClassRegistration> getAllRegisteredClasses() {
		return crgrepo.findAll();
	}

	@Override
	public List<Classes> getAllClasses() {
		return classrepo.findAll();
	}

	
	

	

		

	

	

	

}
