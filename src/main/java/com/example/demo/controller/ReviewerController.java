package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.SeparatePage;
import com.example.demo.digitalSigniture.Signiture;
import com.example.demo.entity.FirstPagePDisplayAndSearch.PaperForShow;
import com.example.demo.entity.PublishArrangement_Editer.AuthorInformation;
import com.example.demo.entity.PublishArrangement_Editer.MyPaperState;
import com.example.demo.entity.PublishArrangement_Editer.PublishArrangement;
import com.example.demo.entity.normal.*;
import com.example.demo.entity.reviewerArrangement_reviewer.ReturnPaper;
import com.example.demo.lang.Result;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.mapper.CertifyMapper;
import com.example.demo.mapper.PaperMapper;
import com.example.demo.service.CertifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * the controller used to deal with request related reviewers
 * </p>
 *
 * @author Team8
 */
@RestController
public class ReviewerController {

    @Autowired
    CertifyMapper certifyMapper;

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    AuthorMapper authorMapper;

    @Autowired
    CertifyService certifyService;

    //审核管理首页界面显示（审阅人） 测试
    @RequestMapping("/reviewer/test")
    public Result reviewArrangement_reviewerTest(){

        IPage<ReturnPaper> iPage = new Page<>(1,2);

        String keywords=null;



        if(keywords!=null) iPage = certifyMapper.getReturnPaperByReviewerIdAndTimeAndKeyword(iPage,40001,"asc","作");
        else iPage = certifyMapper.getReturnPaperByReviewerIdAndTime(iPage,40001,"asc");

        List<ReturnPaper> returnPaperList = iPage.getRecords();

        for(int i=0;i<returnPaperList.size();i++){
            ReturnPaper thePaper = returnPaperList.get(i);
            String n1 = thePaper.getAuthorName1();
            String n2 = thePaper.getAuthorName2();
            String n3 = thePaper.getAuthorName3();
            String name = n1;

            if(n2!=null) {
                name = name.concat(";");
                name = name.concat(n2);

            }

            if(n3!=null){
                name = name.concat(";");
                name = name.concat(n3);
            }

            thePaper.setAuthorName(name);

            returnPaperList.set(i,thePaper);
        }

        iPage.setRecords(returnPaperList);

        //return Result.succ(new Page<>(1,2));
        return Result.succ(iPage);


    }


    /**
     * Get the paper list that the input reviewer needed or already review
     * @param rev_id the id of input reviewer
     * @param currentPage indicate what page the current paper list is in
     * @param numEachPage indicate the number of papers in each page
     * @param keywords if is null, return all papers related to the reviewer, if is not null, return all papers that accord the keywords
     * @param timeOrder if true, order the paper list by paper's create time, if false, ascend order.
     * @return a Result instance, in which is  a page of paper list related the input reviewer
     */
    //审核管理首页界面显示（审阅人）
    @RequestMapping("/reviewer")
    public Result reviewArrangement_reviewer(Integer rev_id, Integer currentPage, Integer numEachPage, String keywords, Boolean timeOrder){


        //get the ************************还没写完注释
        IPage<ReturnPaper> iPage = new Page<>(currentPage,numEachPage);
        IPage<ReturnPaper> iPage1;

        String order = (timeOrder?"desc":"asc");

        if(keywords!=null) iPage1 = certifyMapper.getReturnPaperByReviewerIdAndTimeAndKeyword(iPage,rev_id,order,keywords);
        else iPage1 = certifyMapper.getReturnPaperByReviewerIdAndTime(iPage,rev_id,order);

        List<ReturnPaper> returnPaperList = iPage1.getRecords();

        for(int i=0;i<returnPaperList.size();i++){
            ReturnPaper thePaper = returnPaperList.get(i);
            String n1 = thePaper.getAuthorName1();
            String n2 = thePaper.getAuthorName2();
            String n3 = thePaper.getAuthorName3();
            String name = n1;

            if(n2!=null) {
                name = name.concat(";");
                name = name.concat(n2);

            }

            if(n3!=null){
                name = name.concat(";");
                name = name.concat(n3);
            }

            thePaper.setAuthorName(name);

            returnPaperList.set(i,thePaper);
        }

        iPage1.setRecords(returnPaperList);

        return Result.succ(iPage1);


    }


    //reviewer添加评论和评审结果
    //没过的有comments，过了的有“1”，都评论的是空的
    @RequestMapping("/reviewer/paperReview")
    public Result addReviewResult(){
        Integer papId=20001;
        Integer revId = 40003;
        String common = "That is not OK";
        Boolean isPass = false;

        Certify certify = certifyMapper.getCertifyByPaperId(20001);


        if(isPass==false){
            if(certify.getRevOne()==40003) certify.setCommonOne("That is not OK");
            else if(certify.getRevTwo()==40003) certify.setCommonTwo("That is not OK");
            else if(certify.getRevThree()==40003 ) certify.setCommonThree("That is not OK");
        }else{
            if(certify.getRevOne()==40003) certify.setCommonOne("1");
            else if(certify.getRevTwo()==40003) certify.setCommonTwo("1");
            else if(certify.getRevThree()==40003 ) certify.setCommonThree("1");
        }

        certifyService.updateById(certify);


        int count=0;

        if(certify.getCommonOne()!=null&certify.getCommonTwo()!=null&certify.getCommonThree()!=null){
            if(certify.getCommonOne().equals("1")) count++;
            if(certify.getCommonTwo().equals("1")) count++;
            if(certify.getCommonThree().equals("1")) count++;

            if(count==3){

                certify.setPublicId("Team8");
                certifyService.updateById(certify);
                //这里需要url和文件，之后打数字签名
                Paper paper = paperMapper.getPaperByPaperId(20001);
                String url = paper.getFileUrl();

               // Signiture.creat("src/main/resources/public/pdf/file.pdf","src/main/resources/public/pdf/team8.pfx","team8","name of viewer");
                //demo7里面有数字签名

            }
        }


        return Result.succ("success");

    }
}
