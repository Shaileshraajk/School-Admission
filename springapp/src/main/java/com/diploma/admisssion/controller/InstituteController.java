package com.diploma.admisssion.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.annotations.ApiOperation;
import java.util.List;

import com.diploma.admisssion.exceptions.InstituteAlreadyExistException;
import com.diploma.admisssion.model.Institutes;
import com.diploma.admisssion.service.InstituteService;

@RestController
@RequestMapping("/institutes")
@CrossOrigin
public class InstituteController {

    @Autowired
    private InstituteService instservice;

	Logger logger=LoggerFactory.getLogger(InstituteController.class);


    @GetMapping("/getAll")
	@ApiOperation("Fetch all the Institutes")
    public ResponseEntity<List<Institutes>> getAll(){
        return new ResponseEntity<List<Institutes>>(instservice.getAllInstitutes(),HttpStatus.OK);
    }

    @GetMapping("/getById")
	@ApiOperation("Fetch all the Institutes")
    public ResponseEntity<Institutes> getById(@RequestParam(required = true) int id){
        if(instservice.getInstitutebyId(id)!=null)
        {
            return new ResponseEntity<Institutes>(instservice.getInstitutebyId(id),HttpStatus.OK);
        }
        return new ResponseEntity<Institutes>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
	@ApiOperation("Adding a Institute")
	public ResponseEntity<String> add(@RequestBody Institutes ins) throws InstituteAlreadyExistException{
		Institutes insct=instservice.addInstitute(ins);
		logger.info(insct.toString());
		return new ResponseEntity<>("Institute Added Successfully",HttpStatus.OK);

	}
    @DeleteMapping("/delete/{id}")
	@Transactional
	@ApiOperation("Delete a institute")
	public ResponseEntity<String> deleteInstitute(@PathVariable("id") int id){
		if(instservice.instituteDetails(id)!=null){
				Institutes ird = instservice.instituteDetails(id);
				// System.out.println(ird.toString());
				instservice.deletefromCourses(ird.getInstitutesId());
				instservice.deletefromCourseReg(ird.getInstitueName());
				instservice.deleteByInstituteId(ird.getInstitutesId());
				return  new ResponseEntity<String>("Institute Deleted Successfully", HttpStatus.OK);
		}
		return  new ResponseEntity<>("Institute not able to delete", HttpStatus.NOT_FOUND);
	}
    
	@PutMapping("/edit/{id}")
	@ApiOperation("Editing a Institute")
	public ResponseEntity<Institutes> editInstitute(@PathVariable("id") int id, @RequestBody Institutes ins){
		if(instservice.instituteDetails(id)!=null){
			Institutes insdt = instservice.instituteDetails(id);
			insdt.setInstitutePlace(ins.getInstitutePlace());
			insdt.setImgLocation(ins.getImgLocation());
			Institutes editted = instservice.saveInstitute(insdt);

			return new ResponseEntity<Institutes>(editted,HttpStatus.OK);
		}
		return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/deleteAll")
	@Transactional
	@ApiOperation("Delete all institutes")
	public ResponseEntity<HttpStatus> deleteAllInstitutes(){
		instservice.deleteAll();
		return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
