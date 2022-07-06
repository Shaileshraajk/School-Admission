package com.school.admisssion.service;

import java.util.List;

import javax.mail.MessagingException;

import com.school.admisssion.exceptions.InvalidAdminLogin;
import com.school.admisssion.exceptions.InvalidOTPException;
import com.school.admisssion.exceptions.InvalidUserException;
import com.school.admisssion.exceptions.UserAlreadyExistsException;
import com.school.admisssion.model.User;

public interface UserService {
	
	public User saveUser(User user) throws UserAlreadyExistsException;
	
    public List<User> getAllUsers();
    
    public boolean checkIfUserExist(String email);

    public User loginUser(User user) throws InvalidUserException; 

    //public User loginwithNum(User user) throws InvalidMobileNumException;

    public User AdminLogin(User user) throws InvalidAdminLogin;

    public void SendOTPMail(User user) throws MessagingException;

    public void ValidateOTP(int otp, User user) throws InvalidOTPException;
    
   

}
