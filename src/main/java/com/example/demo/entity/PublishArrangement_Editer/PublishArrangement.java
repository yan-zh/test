package com.example.demo.entity.PublishArrangement_Editer;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.PublishArrangement_Editer.AuthorInformation;
import com.example.demo.entity.PublishArrangement_Editer.MyPaperState;

import java.io.Serializable;

public class PublishArrangement implements Serializable {
    AuthorInformation authorInformation;
    IPage<MyPaperState> ipage;

    public AuthorInformation getAuthorInformation() {
        return authorInformation;
    }

    public void setAuthorInformation(AuthorInformation authorInformation) {
        this.authorInformation = authorInformation;
    }

    public IPage<MyPaperState> getIpage() {
        return ipage;
    }

    public void setIpage(IPage<MyPaperState> ipage) {
        this.ipage = ipage;
    }
}
