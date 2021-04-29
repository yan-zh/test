package com.example.demo.entity.PaperDetail;

import com.example.demo.entity.normal.Author;
import com.example.demo.entity.normal.Institute;
import com.example.demo.entity.normal.Paper;
import lombok.Data;

import java.io.Serializable;

@Data
public class PaperDetail implements Serializable {
    private String pname;
    private String iname;
    private String pabstract;
    private String keyword;
    private String category;
    private String publicID;
    private Integer download;
    private String email;
    private String fileUrl;

    public void absorb(Paper paper, Author author, Institute institute) {
        this.setPname(paper.getPname());
        this.setIname(institute.getIname());
        this.setPabstract(paper.getPabstract());
        this.setKeyword(paper.getKeyWord());
        this.setCategory(paper.getCategory());
        this.setDownload(paper.getDownload());
        this.setEmail(author.getEmail());
        this.setFileUrl(paper.getFileUrl());
    }

}
