package com.school.admisssion.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.admisssion.exceptions.InstituteAlreadyExistException;
import com.school.admisssion.exceptions.InstituteNotFoundException;
import com.school.admisssion.model.Institutes;
import com.school.admisssion.model.UserRatings;
import com.school.admisssion.service.InstituteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.annotations.ApiOperation;
import java.util.List;

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

    @GetMapping("/searchInstitute")
    @ApiOperation("Search Institutes")
    public ResponseEntity<List<Institutes>> searchInstitutebyName(@RequestParam(required = true) String institutename) throws InstituteNotFoundException{
        List<Institutes> institutes = instservice.searchInstitute(institutename);
        return new ResponseEntity<>(institutes,HttpStatus.OK);
    }

    @PostMapping("/rate")
    @ApiOperation("Rate and Review the Institute")
    public ResponseEntity<UserRatings> rateInstitute(@RequestBody UserRatings ur){
        UserRatings ursp = instservice.saveRating(ur);
        logger.info(ursp.toString());

        double sum=0;
        List<UserRatings> userratings = instservice.getInstituteRating(ur.getInstituteName());
        for(UserRatings urr: userratings){
            sum+=urr.getRating();
        }
        double avg = sum/userratings.size();
        Institutes inst = instservice.getInstituteDetails(ur.getInstituteName());
        inst.setRating(avg);
        instservice.saveRating(inst);

        return new ResponseEntity<UserRatings>(ursp,HttpStatus.OK);
    }

    @GetMapping("/getRatingbyInstituteName")
    @ApiOperation("Fetch the rating of the Institute")
    public ResponseEntity<List<UserRatings>> getRatingbyInstituteName(@RequestParam(required = true) String instituteName ){
        List<UserRatings> userratings = instservice.getInstituteRating(instituteName);
        return new ResponseEntity<List<UserRatings>>(userratings,HttpStatus.OK);
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
            insdt.setInstituteDescription(ins.getInstituteDescription());
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
