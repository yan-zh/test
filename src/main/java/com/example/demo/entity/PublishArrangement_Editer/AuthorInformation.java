package com.example.demo.entity.PublishArrangement_Editer;

import com.example.demo.entity.normal.Institute;
import com.example.demo.entity.normal.User;

import java.io.Serializable;

public class AuthorInformation implements Serializable {
    private String lastName;
    private String firstName;
    private String authorInstitute;
    private String image;
    private String rtype;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAuthorInstitute() {
        return authorInstitute;
    }

    public void setAuthorInstitute(String authorInstitute) {
        this.authorInstitute = authorInstitute;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }



    public void absorb(User user, Institute institute){

        if(user!=null & institute!=null){
            this.lastName = user.getLastname();
            this.firstName = user.getFirstname();
            this.authorInstitute = institute.getIname();
            this.image = user.getImage();
            this.rtype = user.getRname();
        }else{
            System.out.println("user or institute absorbed by authorInformation is null");
        }



    }
}
