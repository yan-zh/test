package com.example.demo.entity.PublishArrangement_Editer;

import com.example.demo.entity.normal.Paper;
import lombok.Data;

import java.io.Serializable;
@Data
public class MyPaperState implements Serializable {
    private Integer pap_id;
    private String pname;
    private String category;
    private String keyword;
    private String authorName;
    private String publicId;

    public MyPaperState(Paper paper){
        this.pap_id = paper.getPaperId();
        this.pname = paper.getPname();
        this.category = paper.getCategory();
        this.keyword = paper.getKeyWord();
    }

    public MyPaperState(){

    }



}
