package com.school.admisssion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.admisssion.model.Admissions;

@Repository
public interface AdmissionsRepo extends JpaRepository<Admissions, Integer>{

    Admissions findByTitleAndUseremail(String title, String useremail);

    Admissions findByRegid(int regid);
    
}
