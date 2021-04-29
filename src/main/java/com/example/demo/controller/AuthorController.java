package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.SeparatePage;
import com.example.demo.entity.PublishArrangement_Editer.AuthorInformation;
import com.example.demo.entity.PublishArrangement_Editer.MyPaperState;
import com.example.demo.entity.PublishArrangement_Editer.PublishArrangement;
import com.example.demo.entity.normal.*;
import com.example.demo.lang.Result;
import com.example.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  The controller that deal with the request related Authors' action
 * </p>
 *
 * @author Team8
 */
@RestController
public class AuthorController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthorMapper authorMapper;

    @Autowired
    InstituteMapper instituteMapper;

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    CertifyMapper certifyMapper;

    //“发表管理”论文首页显示（作者）  成功！！！！！！！！！！！！！！
    @RequestMapping("/author/test")
    public Result uploadedPaperTest(){

        //应该用传入参数aut——id
        User user = userMapper.getUserByAutId(30001);
        //应该用传入参数aut——id
        Author author = authorMapper.getAuthorById(30001);
        Institute institute = instituteMapper.getInstituteById(author.getInsId());

        AuthorInformation authorInformation = new AuthorInformation();
        authorInformation.absorb(user,institute);

        //传入AUT——id
       // List<Paper> papers = paperMapper.getPaperByAutId(30001);
        String keywords1 = "dd";
        Boolean timeOrder1 = false;

        List<Paper> papers;
        if(timeOrder1==true){
            if(keywords1==null){
                papers = paperMapper.getPaperByAutIdATime(30001,"desc");
            }else{
                papers = paperMapper.getPaperByAutIdATimeAKeyword(30001,"desc", "作");
            }
        }else{
            if(keywords1==null){
                papers = paperMapper.getPaperByAutIdATime(30001,"asc");
            }else{
                papers = paperMapper.getPaperByAutIdATimeAKeyword(30001,"asc", "作");
            }
        }

        List<MyPaperState> myPaperStates = new ArrayList<>();

        for(int i=0;i<papers.size();i++){
            Paper paper = papers.get(i);
            String name=" ";

            if(paper.getAutidOne()!=null){
                name = authorMapper.getAuthorById(paper.getAutidOne()).getAname();
            }

            if(paper.getAutidTwo()!=null){
                name = name+";";
                name = name+(authorMapper.getAuthorById(paper.getAutidTwo()).getAname());
            }

            if(paper.getAutidThree()!=null){
                name = name+";";
                name = name+(authorMapper.getAuthorById(paper.getAutidThree()).getAname());
            }


            Certify certify = certifyMapper.getCertifyByPaperId(paper.getPaperId());

            MyPaperState myPaperState = new MyPaperState(paper);
            myPaperState.setAuthorName(name);
            myPaperState.setPublicId(certify.getPublicId());
            myPaperStates.add(myPaperState);
        }

        PublishArrangement publishArrangement = new PublishArrangement();



        IPage<MyPaperState> ipage = new Page<>(1,2);
        ipage.setRecords(SeparatePage.startPage(myPaperStates,1,2));

        publishArrangement.setAuthorInformation(authorInformation);
        publishArrangement.setIpage(ipage);

        return Result.succ(publishArrangement);
    }


    /**
     * Return the information of the author and a paged list of paper written by the author
     * @param aut_id the id of input author
     * @param currentPage indicates the return paper list is in what page
     * @param numEachPage indicates the number of papers in each page
     * @param keywords if is null, return all papers written by input author, if is not null, return all papers that accord the keywords
     * @param timeOrder if true, order the paper list by peper create time, if false, ascend order.
     * @return a Result instance, in which is a group of author information and a page of paper list
     */
    //“发表管理”论文首页显示（作者）
    @GetMapping("/author")
    public Result uploadedPaper(Integer aut_id, Integer currentPage, Integer numEachPage, String keywords, Boolean timeOrder){

        //get all the needed information related to input author
        User user = userMapper.getUserByAutId(aut_id);
        Author author = authorMapper.getAuthorById(aut_id);
        Institute institute = instituteMapper.getInstituteById(author.getInsId());

        //capsule the author information in to AuthorInformation instance
        AuthorInformation authorInformation = new AuthorInformation();
        authorInformation.absorb(user,institute);

        //execute SQL statement according to the input parameter
        String order = (timeOrder?"desc":"asc");

        List<Paper> papers;

        if(keywords==null) papers = paperMapper.getPaperByAutIdATime(aut_id,order);
        else papers = paperMapper.getPaperByAutIdATimeAKeyword(aut_id,order, keywords);


        //prepare for capsule the paper list
        List<MyPaperState> myPaperStates = new ArrayList<>();

        //put all the author name into the form: "author1;author2;author3"
        for(int i=0;i<papers.size();i++){
            Paper paper = papers.get(i);
            String name=" ";

//            if(paper.getAutidOne()!=null){
//                name = authorMapper.getAuthorById(paper.getAutidOne()).getAname();
//            }
//
//            if(paper.getAutidTwo()!=null){
//                name = name+";";
//                name = name+(authorMapper.getAuthorById(paper.getAutidTwo()).getAname());
//            }
//
//            if(paper.getAutidThree()!=null){
//                name = name+";";
//                name = name+(authorMapper.getAuthorById(paper.getAutidThree()).getAname());
//            }

            if(paper.getAutidOne()!=null){
                name = authorMapper.getAuthorById(paper.getAutidOne()).getAname();

                if(paper.getAutidTwo()!=null){
                    name = name+";";
                    name = name+(authorMapper.getAuthorById(paper.getAutidTwo()).getAname());

                    if(paper.getAutidThree()!=null){
                        name = name+";";
                        name = name+(authorMapper.getAuthorById(paper.getAutidThree()).getAname());
                    }
                }
            }


            //get the publicID which is stored into table certify
            Certify certify = certifyMapper.getCertifyByPaperId(paper.getPaperId());

            //add the publicID into paper list
            MyPaperState myPaperState = new MyPaperState(paper);
            myPaperState.setAuthorName(name);
            myPaperState.setPublicId(certify.getPublicId());
            myPaperStates.add(myPaperState);
        }


        //add the adjusted record into Page class
        IPage<MyPaperState> ipage = new Page<>(currentPage,numEachPage);
        ipage.setRecords(SeparatePage.startPage(myPaperStates,currentPage,numEachPage));
        ipage.setTotal(myPaperStates.size());

        //final capsule the data
        PublishArrangement publishArrangement = new PublishArrangement();
        publishArrangement.setAuthorInformation(authorInformation);
        publishArrangement.setIpage(ipage);

        return Result.succ(publishArrangement);
    }



}
