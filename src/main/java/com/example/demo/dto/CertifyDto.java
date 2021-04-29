package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CertifyDto implements Serializable {
    private Integer rev_id;
    private Integer pap_id;
    private String common;
    private Boolean isPass;


}
