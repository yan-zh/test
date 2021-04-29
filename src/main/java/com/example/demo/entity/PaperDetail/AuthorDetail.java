package com.example.demo.entity.PaperDetail;

import lombok.Data;

import java.io.Serializable;
import java.security.PrivateKey;
@Data
public class AuthorDetail implements Serializable {
    private String aname;
    private String uname;
    private String introduction;
    private String Image;
    private Integer autId;
}
