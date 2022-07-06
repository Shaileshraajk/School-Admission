package com.school.admisssion.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.admisssion.exceptions.InvalidAdminLogin;
import com.school.admisssion.exceptions.InvalidOTPException;
import com.school.admisssion.exceptions.InvalidUserException;
import com.school.admisssion.exceptions.UserAlreadyExistsException;
import com.school.admisssion.model.User;
import com.school.admisssion.service.UserService;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserContoller {

	Logger logger = LoggerFactory.getLogger(UserContoller.class);

	@Autowired
	private UserService userservice;
	
	@PostMapping("/add")
	@ApiOperation("Add users to the Portal")
	public ResponseEntity<User> add(@RequestBody User user) throws UserAlreadyExistsException{
		User uresp = userservice.saveUser(user);
		logger.info(uresp.toString());
		return new ResponseEntity<User>(uresp,HttpStatus.OK);

	}

	@PostMapping("/login")
	@ApiOperation("Login users the Portal")
	public ResponseEntity<User> login(@RequestBody User user) throws InvalidUserException{
		User uresp = userservice.loginUser(user);
		logger.info("Logged in with: "+uresp.getEmail());	
		return new ResponseEntity<User>(uresp,HttpStatus.OK);

	}

	// @PostMapping("/loginwithnum")
	// @ApiOperation("Admin login into the Portal")
	// public ResponseEntity<String> loginwithnum(@RequestBody User user) throws InvalidMobileNumException{
	// 	User uresp = userservice.loginwithNum(user);
	// 	logger.info("Logged in as Admin: "+uresp.getName());
	// 	return new ResponseEntity<String>("Admin",HttpStatus.OK);
	// }
	
	@PostMapping("/loginasAdmin")
	@ApiOperation("Admin login into the Portal")
	public ResponseEntity<User> loginasAdmin(@RequestBody User user) throws InvalidAdminLogin, MessagingException{
		User uresp = userservice.AdminLogin(user);
		userservice.SendOTPMail(uresp);
		logger.info("OTP Sent to "+uresp.getEmail());
		return new ResponseEntity<User>(uresp,HttpStatus.OK);
	}

	@PostMapping("/validateAdminOTP")
	@ApiOperation("Validates the Admin OTP")
	public ResponseEntity<String> validateAdminOTP(@RequestBody User user, @RequestParam (required = true) int otp) throws InvalidOTPException{
		userservice.ValidateOTP(otp, user);
		logger.info("Logged in as "+user.getName());
		return new ResponseEntity<String>("Admin Login Successful",HttpStatus.OK);
	}

	
	
	@GetMapping("/getAll")
	@ApiOperation("Fetch all the records of users")
	public ResponseEntity<List<User>> list(){
        return new ResponseEntity<List<User>>(userservice.getAllUsers(),HttpStatus.OK);
    }
	

}
