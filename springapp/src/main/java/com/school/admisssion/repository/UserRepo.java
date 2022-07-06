package com.school.admisssion.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.admisssion.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	User findByEmail(String email);
	
	User findByMobno(String mobno);

	User findByEmailAndPwd(String email, String pwd);
	
	
	
	

}
