package com.school.admisssion.exceptions;

public class InvalidOTPException extends Exception{

    public InvalidOTPException(){
        super("OTP is invalid");
    }
    
}
