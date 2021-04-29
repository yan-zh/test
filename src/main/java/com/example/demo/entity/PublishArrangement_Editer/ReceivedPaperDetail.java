package com.example.demo.entity.PublishArrangement_Editer;

import lombok.Data;

import java.io.Serializable;
@Data
public class ReceivedPaperDetail implements Serializable {
    private String pname;
    private String pabstract;
    private String keyword;
    private String category;
    private String file_url;

}
