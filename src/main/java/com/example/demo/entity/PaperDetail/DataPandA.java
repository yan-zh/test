package com.example.demo.entity.PaperDetail;

import com.example.demo.entity.PaperDetail.AuthorDetail;
import com.example.demo.entity.PaperDetail.PaperDetail;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataPandA implements Serializable {
    private PaperDetail paperDetail;
    private List<AuthorDetail> authorDetails;

    public DataPandA(PaperDetail paperDetail, List<AuthorDetail> authorDetails){
        this.authorDetails = authorDetails;
        this.paperDetail = paperDetail;
    }
}
