package com.school.admisssion.exceptions;

public class InstituteNotFoundException extends Exception{

    public InstituteNotFoundException(String institute){
        super(institute+" Institute not found");
    }

    
}
