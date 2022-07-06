package com.school.admisssion.exceptions;

public class InvalidUserException extends Exception{

    public InvalidUserException(){
        super("Inavlid Login Credentials");
    }
    
}
