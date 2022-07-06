package com.school.admisssion.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.school.admisssion.model.ClassRegistration;
import com.school.admisssion.model.Classes;
import com.school.admisssion.service.ClassService;
import com.school.admisssion.service.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/classes")
@CrossOrigin
public class ClassController {
	
	@Autowired
	private ClassService classservice;

	@Autowired
	private FileStorageService fileStorageService;
	
	@GetMapping("/getAll")
	@ApiOperation("Fetch all the records of classes")
	public ResponseEntity<List<Classes>> getAll(){
		return new ResponseEntity<List<Classes>>(classservice.getAllClasses(),HttpStatus.OK);
	}
	
	@GetMapping("/getbyClassno")
	@ApiOperation("Fetch the class by title")
	public ResponseEntity<List<Classes>> getbyTitle(@RequestParam(required = true) String classno){
		
		List<Classes> classes = new ArrayList<Classes>();
		classservice.getClassbyClassno(classno).forEach(classes::add);
		if(classes.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(classes, HttpStatus.OK);
		
	}

	@GetMapping("/getbyId")
	@ApiOperation("Fetch the class by Class id")
	public ResponseEntity<Classes> getbyId(@RequestParam(required = true) int id){
		if(classservice.classDetails(id)!=null){
			return  new ResponseEntity<Classes>(classservice.classDetails(id), HttpStatus.OK);
		}
		return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/add")
	@ApiOperation("Adding a Class")
	public ResponseEntity<String> add(@RequestBody Classes crs){
		if(classservice.addClass(crs)!=null){
			return  new ResponseEntity<String>("Class added successfully", HttpStatus.OK);
		}
		return  new ResponseEntity<>("Class was already added", HttpStatus.ALREADY_REPORTED);
	}

	@PutMapping("/edit/{id}")
	@ApiOperation("Editing a Class")
	public ResponseEntity<Classes> editCourse(@PathVariable("id") int id, @RequestBody Classes crs){
		if(classservice.classDetails(id)!=null){
			Classes crsdt = classservice.classDetails(id);
			crsdt.setInstituteid(crs.getInstituteid());
			crsdt.setInstitute_name(crs.getInstitute_name());
			crsdt.setAcademicYear(crs.getAcademicYear());
			crsdt.setElgibleMarks(crs.getElgibleMarks());
			Classes editted = classservice.saveClass(crsdt);

			List<ClassRegistration> coursereg = classservice.getClassRegDetails(crsdt.getClassno());
			for(ClassRegistration crgs:coursereg){
				crgs.setAcademicYear(crsdt.getAcademicYear());
				crgs.setInstituteName(crsdt.getInstitute_name());
				crgs.setEligibleMarks(crsdt.getElgibleMarks());
				classservice.saveRegistration(crgs);
			}

			return new ResponseEntity<Classes>(editted,HttpStatus.OK);
		}
		return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/delete/{id}")
	@Transactional
	@ApiOperation("Delete a class")
	public ResponseEntity<String> deleteCourse(@PathVariable("id") int id){
		if(classservice.classDetails(id)!=null){
				Classes crd = classservice.classDetails(id);
				classservice.deletefromClassReg(crd.getClassno());
				classservice.deletebyId(id);
				return  new ResponseEntity<String>("Class Deleted Successfully", HttpStatus.OK);
		}
		return  new ResponseEntity<>("Class not able to delete", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteAll")
	@Transactional
	@ApiOperation("Delete all classes")
	public ResponseEntity<HttpStatus> deleteAllCourse(){
		classservice.deleteAll();
		return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	@PostMapping("/enroll")
	@ApiOperation("Enroll into a new class")
	public ResponseEntity<ClassRegistration> enroll(@RequestBody ClassRegistration crg) {
		if(classservice.enrollClass(crg)!=null)
		{
			classservice.enrollClass(crg);
			if(classservice.classRegistrationDetails(crg.getUseremail(), crg.getClassno())!=null){
				ClassRegistration crgs = classservice.classRegistrationDetails(crg.getUseremail(), crg.getClassno());
				return new ResponseEntity<ClassRegistration>(crgs,HttpStatus.OK);
			}
			return new ResponseEntity<ClassRegistration>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ClassRegistration>(HttpStatus.IM_USED);
	}
	
	@GetMapping("/viewenrolled")
	@ApiOperation("View enrolled classes")
	public ResponseEntity<List<ClassRegistration>> viewenrolled(@RequestParam(required = true)String useremail){
		
		List<ClassRegistration> enrolled = new ArrayList<ClassRegistration>();
		enrolled = classservice.viewenrolled(useremail);
		if(enrolled.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(enrolled,HttpStatus.OK);
	}

	@GetMapping("/enrolledbyid")
	@ApiOperation("View enrolled class by Registarion id")
	public ResponseEntity<ClassRegistration> viewenrolledbyId(@RequestParam(required = true)int regid){
		
		ClassRegistration enrolledcourse = classservice.classRegistrationDetails(regid);
		if(enrolledcourse!=null){
			return new ResponseEntity<>(enrolledcourse,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/deleteenrolled")
	@Transactional
	@ApiOperation("Delete enrolled classes")
	public ResponseEntity<String> deleteCourse(@RequestParam(required = true) int regid, @RequestParam(required = true) String useremail){
		if(classservice.deleteenrolled(regid,useremail)>0)
		{
			return new ResponseEntity<>("Class Unenrolled Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getAllregistered")
	@ApiOperation("View Registred Class List")
	public ResponseEntity<List<ClassRegistration>> getregisteredlist(){
		if(classservice.getAllRegisteredClasses().size()>0){
			return new ResponseEntity<>(classservice.getAllRegisteredClasses(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/uploadMarks")
	@ApiOperation("Upload Marksheet")
	public ResponseEntity<String> uploadMarks(@RequestParam("file") MultipartFile file) throws IOException{
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/courses/")
				.path(fileName)
				.toUriString();
		System.out.println("fileDownloadUri: "+fileDownloadUri);
		return new ResponseEntity<String>(fileDownloadUri,HttpStatus.OK);
	}

	@GetMapping("/{fileName:.+}")
	@ApiOperation("Get Marksheet")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,HttpServletRequest request) throws IOException{
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		if(contentType==null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.body(resource);
	}
	
	

}
