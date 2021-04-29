package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CertifyDto implements Serializable {
    private Integer rev_id;
    private Integer pap_id;
    private String common;
    private Boolean isPass;

    public Integer getRev_id() {
        return rev_id;
    }

    public void setRev_id(Integer rev_id) {
        this.rev_id = rev_id;
    }

    public Integer getPap_id() {
        return pap_id;
    }

    public void setPap_id(Integer pap_id) {
        this.pap_id = pap_id;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public Boolean getPass() {
        return isPass;
    }

    public void setPass(Boolean pass) {
        isPass = pass;
    }
}
