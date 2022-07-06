package com.school.admisssion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Institutes {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    private int institutesid;
    private String institueName;
    private String institutePlace;
    private String imgLocation;
    private double rating;
    private String InstituteDescription;


    public String getInstituteDescription() {
        return InstituteDescription;
    }

    public void setInstituteDescription(String instituteDescription) {
        InstituteDescription = instituteDescription;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    

    public Institutes() {

    }

    public int getInstitutesId() {
        return institutesid;
    }

    public void setInstitutesId(int institutesid) {
        this.institutesid = institutesid;
    }

    public String getInstitueName() {
        return institueName;
    }

    public void setInstitueName(String institueName) {
        this.institueName = institueName;
    }

    public String getInstitutePlace() {
        return institutePlace;
    }

    public void setInstitutePlace(String institutePlace) {
        this.institutePlace = institutePlace;
    }

    public String getImgLocation() {
        return imgLocation;
    }

    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }

    @Override
    public String toString() {
        return "Institutes [InstituteDescription=" + InstituteDescription + ", imgLocation=" + imgLocation
                + ", institueName=" + institueName + ", institutePlace=" + institutePlace + ", institutesid="
                + institutesid + ", rating=" + rating + "]";
    }

    



    
    
}
