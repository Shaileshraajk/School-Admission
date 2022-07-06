package com.school.admisssion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.admisssion.model.ClassRegistration;


@Repository
public interface ClassRegRepo extends JpaRepository<ClassRegistration, Integer>{

	ClassRegistration findByUseremailAndClassno(String useremail, String classno);

	List<ClassRegistration> findByUseremail(String useremail);

	ClassRegistration findByRegid(int regid);

	List<ClassRegistration> findByClassno(String classno);

	int deleteByClassno(String classno);
	
	int deleteByRegidAndUseremail(int regid, String useremail);

	int deleteByInstituteName(String institute_name);

}
