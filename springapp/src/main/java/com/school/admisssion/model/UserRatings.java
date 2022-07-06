package com.school.admisssion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserRatings {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    private int ratingId;
    private String email;
    private String name;
    private String institutename;
    private String review;
    private double rating;

    public UserRatings() {
    }

    

    @Override
    public String toString() {
        return "UserRatings [email=" + email + ", institutename=" + institutename + ", name=" + name + ", rating="
                + rating + ", ratingId=" + ratingId + ", review=" + review + "]";
    }



    public String getInstituteName() {
        return institutename;
    }

    public void setInstituteName(String institutename) {
        this.institutename = institutename;
    }
    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    

    

    
}
