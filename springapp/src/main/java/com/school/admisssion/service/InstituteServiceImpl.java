package com.school.admisssion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.admisssion.exceptions.InstituteAlreadyExistException;
import com.school.admisssion.exceptions.InstituteNotFoundException;
import com.school.admisssion.model.Institutes;
import com.school.admisssion.model.UserRatings;
import com.school.admisssion.repository.ClassRegRepo;
import com.school.admisssion.repository.ClassRepo;
import com.school.admisssion.repository.InstituteRepo;
import com.school.admisssion.repository.RatingsRepo;

@Service
public class InstituteServiceImpl  implements InstituteService{

    @Autowired
    private InstituteRepo instrepo;
    @Autowired
	private ClassRegRepo crgrepo;
    @Autowired
	private ClassRepo courserepo;

    @Autowired
    private RatingsRepo raterepo;

    @Override
    public List<Institutes> getAllInstitutes() {
        return instrepo.findAll();
    }

    @Override
    public Institutes getInstitutebyId(int id) {
        if(instrepo.findByInstitutesid(id)!=null)
        {
            return instrepo.findByInstitutesid(id);
        }
        return null;
    }

    @Override
    public UserRatings saveRating(UserRatings ur) {
        return raterepo.save(ur);
    }

    @Override
    public List<UserRatings> getInstituteRating(String institutename) {
        return raterepo.findByInstitutename(institutename);
    }

    @Override
    public Institutes getInstituteDetails(String instituteName) {
        return instrepo.findByInstitueName(instituteName);
    }

    @Override
    public Institutes saveRating(Institutes inst) {
        return instrepo.save(inst);
    }

    @Override
    public Institutes getInstitutebyName(String institutename) throws InstituteNotFoundException {
        if(instrepo.findByInstitueName(institutename)!=null){
            return instrepo.findByInstitueName(institutename);
        }
        throw new InstituteNotFoundException(institutename);
    }

    @Override
    public List<Institutes> searchInstitute(String instituteName)  throws InstituteNotFoundException{
        List<Institutes> instiutes = new ArrayList<>();
        instrepo.findByInstitueNameContaining(instituteName).forEach(instiutes::add);
        if(instiutes.isEmpty()){
            throw new InstituteNotFoundException(instituteName);
        }
        return instiutes;
    }
    
   
    @Override
    public Institutes addInstitute(Institutes ins) throws InstituteAlreadyExistException{
        
        if(instrepo.findByInstitueName(ins.getInstitueName())!=null){
            throw new InstituteAlreadyExistException(ins.getInstitueName());
              }
		   return instrepo.save(ins);
	   }

    @Override
    public Institutes instituteDetails(int id) {
        
        if(instrepo.findByInstitutesid(id)!=null){
			return instrepo.findByInstitutesid(id);
		}
		return null;
    }

    @Override
    public int deletefromCourseReg(String institute_name) {
        
        return crgrepo.deleteByInstituteName(institute_name);
    }

    @Override
    public int deleteByInstituteId(int id) {
        
        return instrepo.deleteByInstitutesid(id);
    }

    @Override
    public int deletefromCourses(int institutesid) {
        return courserepo.deleteByInstituteid(institutesid);
    }

    @Override
    public Institutes saveInstitute(Institutes ins) {
        
        return instrepo.save(ins);
    }

    @Override
    public void deleteAll() {
        instrepo.deleteAll();
    }

    @Override
    public Institutes findByInstituteName(String instituteName)throws InstituteAlreadyExistException {
        if(instrepo.findByInstitueName(instituteName)!=null){
            throw new InstituteAlreadyExistException(instituteName);
        }
        return instrepo.findByInstitueName(instituteName);
    }
}
