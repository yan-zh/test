package com.example.demo.entity.FirstPagePDisplayAndSearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class PaperForShow implements Serializable {
    private Integer paperId;
    private String pname;
    private Date pCreateTime;
    private String autOne;//这里要名字，不是id
    private String autTwo;
    private String autThree;
    private String pabstract;
    private String keyWord;
    private String category;
}
