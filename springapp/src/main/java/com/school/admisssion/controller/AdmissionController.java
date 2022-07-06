package com.school.admisssion.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

import com.itextpdf.text.*;
import com.school.admisssion.exceptions.AdmittedAlreadyException;
import com.school.admisssion.model.Admissions;
import com.school.admisssion.service.AdmissionService;
import com.school.admisssion.service.ClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admission")
@CrossOrigin
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private ClassService courseservice;



    Logger logger = LoggerFactory.getLogger(AdmissionController.class);

    @PostMapping("/add")
    @Transactional
    @ApiOperation("Add Admission Details")
    public ResponseEntity<Admissions> addAdmission(@RequestBody Admissions adst) throws AdmittedAlreadyException, FileNotFoundException, DocumentException, IOException, MessagingException{
        
        Admissions adresp = admissionService.saveAdmission(adst);
        logger.info(adresp.toString());

        if(adresp.getAdmissionstatus().equals("Granted")){

            admissionService.draftPDF(adresp);
            admissionService.sendEmailWithReport(adresp);
        }
        else{

            admissionService.sendEmail(adresp);
        }

        courseservice.deleteenrolled(adresp.getRegid(), adresp.getUseremail());

        return new ResponseEntity<Admissions>(adresp,HttpStatus.OK);
    }
    
}
