package com.school.admisssion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.admisssion.model.UserRatings;

import java.util.List;

@Repository
public interface RatingsRepo extends JpaRepository<UserRatings, Integer>{

    List<UserRatings> findByInstitutename(String institutename);
    
}
