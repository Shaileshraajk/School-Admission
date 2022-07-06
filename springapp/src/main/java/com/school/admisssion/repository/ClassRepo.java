package com.school.admisssion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.admisssion.model.Classes;

@Repository
public interface ClassRepo extends JpaRepository<Classes, Integer>{
	
	List<Classes> findByClassnoContaining(String classno);

	Classes findByClassid(int classid);

	Classes findByClassnoAndInstituteid(String classno, int instituteid);

	int deleteByClassid(int classid);

	int deleteByInstituteid(int institutesid);

}
