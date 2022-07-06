package com.school.admisssion.exceptions;

public class AdmittedAlreadyException extends Exception{

    public AdmittedAlreadyException(String title, String email){
        super("Admission Already processed for: "+email+" with the title as "+title);
    }
    
}
