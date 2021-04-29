package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaperAddDto implements Serializable {

    private Integer paper_id;

    private  Integer autid_one;

    private String pname;

    private String pabstract;

    private String keyword;

    private String file_url;

    private Integer autid_two;

    private Integer autid_three;

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

    public Integer getAutid_one() {
        return autid_one;
    }

    public void setAutid_one(Integer autid_one) {
        this.autid_one = autid_one;
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

    public Integer getAutid_two() {
        return autid_two;
    }

    public void setAutid_two(Integer autid_two) {
        this.autid_two = autid_two;
    }

    public Integer getAutid_three() {
        return autid_three;
    }

    public void setAutid_three(Integer autid_three) {
        this.autid_three = autid_three;
    }
}
