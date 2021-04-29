package com.example.demo.entity.reviewerArrangement_reviewer;

import com.example.demo.entity.normal.Author;
import com.example.demo.entity.normal.Paper;
import com.example.demo.entity.normal.Reviewer;
import lombok.Data;

import java.io.Serializable;
@Data
public class ReturnPaper implements Serializable {
    private Integer papId;
    private String pname;
    private String category;
    private String authorName;
    private String reviewerName;
    private String publicId;
    private String keyword;
    private String authorName1;
    private String authorName2;
    private String authorName3;
}
