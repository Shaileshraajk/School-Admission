package com.school.admisssion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.admisssion.model.Institutes;

import java.util.List;

@Repository
public interface InstituteRepo extends JpaRepository<Institutes, Integer>{

    Institutes findByInstitutesid(int institutesid);

    Institutes findByInstitueName(String institueName);

    List<Institutes> findByInstitueNameContaining(String institueName);
    
    Institutes findByInstitutesidAndInstitueName(int institutesid, String institute_name);
    int deleteByInstitutesid(int institutesid);
    
    
}
