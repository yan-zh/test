package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterDto implements Serializable {

    private String uname;

    private String password;

    private String lastname;

    private String firstname;

    private String email;

    private String tel;

    private String rname;

    private String category;

    private String universityID;

    private String instituteID;



}
