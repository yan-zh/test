package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaperEditDto implements Serializable {

    private Integer paper_id;

    private  Integer autid;

    private String pname;

    private String pabstract;

    private String keyword;

    private String file_url;



    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }


    public Integer getAutid() {
        return autid;
    }

    public void setAutid(Integer autid) {
        this.autid = autid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPabstract() {
        return pabstract;
    }

    public void setPabstract(String pabstract) {
        this.pabstract = pabstract;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }


}
