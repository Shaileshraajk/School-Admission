package com.school.admisssion.exceptions;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(String email){
        super("User alreday registered with this e-mail: "+email);
    }
    
}
