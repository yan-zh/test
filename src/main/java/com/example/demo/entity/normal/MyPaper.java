package com.example.demo.entity.normal;

import lombok.Data;

import java.io.Serializable;


@Data

public class MyPaper implements Serializable {

    private Integer paperId;//所有的实体名字要用camel命名，这样就可以自动对接数据库中的aa_bb命名

    private String aname;

    private String pname;

    private String abstracts;



}