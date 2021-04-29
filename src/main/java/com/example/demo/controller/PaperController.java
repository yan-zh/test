package com.example.demo.controller;



import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.PaperAddDto;
import com.example.demo.dto.PaperEditDto;
import com.example.demo.entity.FirstPagePDisplayAndSearch.PaperForShow;
import com.example.demo.entity.PaperDetail.AuthorDetail;
import com.example.demo.entity.PaperDetail.DataPandA;
import com.example.demo.entity.PaperDetail.PaperDetail;
import com.example.demo.entity.normal.Author;
import com.example.demo.entity.normal.Institute;
import com.example.demo.entity.normal.Paper;

import com.example.demo.lang.Result;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.mapper.InstituteMapper;
import com.example.demo.mapper.PaperMapper;
import com.example.demo.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * the controller used to deal with request about paper
 * </p>
 *
 * @author Team8
 */
@RestController
public class PaperController {

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    AuthorMapper authorMapper;

    @Autowired
    InstituteMapper instituteMapper;

    @Autowired
    PaperService paperService;

    //接口名：首页界面的论文显示//还要把mapping地址的1取消  成功！！！！！！！！！！！！！！
    @RequestMapping("/papers1/test")
    public Result getAllPapaerByIpageTest(){ //public Result getAllPapaerByIpage(int currentPage, int numeachPage){
        IPage<PaperForShow> iPage = new Page<>(1,6);
        IPage<PaperForShow> iPage1 = paperMapper.getPaperListForShow(iPage);
        return Result.succ(iPage1);
    }

    /**
     * get the paged paper list for visitors of the website
     * @param currentPage indicate what page the current paper list is in
     * @param numEachPage indicate the number of papers in each page
     * @return a Result instance, in which is a list of paper
     */
    //接口名：首页界面的论文显示//还要把mapping地址的1取消
    @GetMapping("/papers")
    public Result getAllPapaerByIpage(Integer currentPage, Integer numEachPage){
        IPage<PaperForShow> iPage = new Page<>(currentPage,numEachPage);
        //get the paper list of all papers
        IPage<PaperForShow> iPage1 = paperMapper.getPaperListForShow(iPage);
        return Result.succ(iPage1);
    }




    //接口名：首页页面的论文查询  成功！！！！！！！！！！！！
    @RequestMapping("/search/test")
    public Result searchPaperTest(){
        IPage<PaperForShow> iPage = new Page<>(1,3);

        IPage<PaperForShow> iPage1;


        iPage1 = paperMapper.searchPaperByNameForShow(iPage,"A");



        //IPage iPage1 = paperMapper.searchPaper(iPage, ruleForm.getInput(),ruleForm.getSelect());


        List<PaperForShow> list = iPage1.getRecords();


        for(Iterator<PaperForShow> it = list.iterator(); it.hasNext();){
            PaperForShow  paper = it.next();
            String abstracts = paper.getPabstract();

            int size = abstracts.length();
            if(size>50){
                size=50;
                abstracts = abstracts.substring(0,size);
                abstracts = abstracts.concat("...");
                paper.setPabstract(abstracts);
            }

        }

        iPage1.setRecords(list);

        return Result.succ(iPage1);
    }


    /**
     * Search papers according to the author name, keywords or paper title
     * @param select what kind of field the user want to search, author name, keywords, or paper title can be input
     * @param input in the chosen field, the users' input
     * @return a Result instance, in which is a list of paper meat with the search condition
     */
    //接口名：首页页面的论文查询
    @GetMapping("/papersSearch")
    public Result searchPaper(String select, String input){


        IPage<PaperForShow> iPage = new Page<>(1,6);
        //IPage ipage1;
        IPage<PaperForShow> iPage1;

        //if the users want search by author name, do a special SQL statement because of the design of the database
        if(select.equals("author")) iPage1 = paperMapper.searchPaperByNameForShow(iPage,input);
        else iPage1 = paperMapper.searchPaperForShow(iPage, input,select);

        //IPage iPage1 = paperMapper.searchPaper(iPage, ruleForm.getInput(),ruleForm.getSelect());


        //if the abstracts of papers are two long, get the first 50 characters and appends "..."
        List<PaperForShow> list = iPage1.getRecords();

        for(Iterator<PaperForShow> it = list.iterator(); it.hasNext();){
            PaperForShow  paper = it.next();
            String abstracts = paper.getPabstract();

            int size = abstracts.length();
            if(size>50){
                size=50;
                abstracts = abstracts.substring(0,size);
                abstracts = abstracts.concat("...");
                paper.setPabstract(abstracts);
            }

        }
        iPage1.setRecords(list);

        return Result.succ(iPage1);
    }


    //论文详情页面  成功！！！！！！！！！！！
    //这里需要修正mapping方式为post来实际使用
    @RequestMapping("/papers/test1")
    public Result getOneDetailedPaperTest(Integer paperId){

        Paper onePaper = paperMapper.getPaperByPaperId(20001);

        List<AuthorDetail> authorDetails = authorMapper.getAuthorDetail(onePaper.getAutidOne(),onePaper.getAutidTwo(),onePaper.getAutidThree());

        Institute institute = instituteMapper.getInstituteById(authorMapper.getAuthorById(onePaper.getAutidOne()).getInsId());

        PaperDetail paperDetail = new PaperDetail();
        paperDetail.absorb(onePaper,authorMapper.getAuthorById(onePaper.getAutidOne()),institute);

        DataPandA dataPandA = new DataPandA(paperDetail,authorDetails);

        return Result.succ(dataPandA);


    }

    /**
     * Return the detailed paper information of the input paper id
     * @param paperId the input paperId used to fetch the detailed information
     * @return a Result instance, in which is the detailed information of the paper, including the author information and paper information
     */
    //论文详情页面
    //这里需要修正mapping方式为post来实际使用
    @GetMapping("/paperDetail")
    public Result getOneDetailedPaper(Integer paperId){

        //get the information from database and encapsulate

        //author information
        Paper onePaper = paperMapper.getPaperByPaperId(paperId);
        List<AuthorDetail> authorDetails = authorMapper.getAuthorDetail(onePaper.getAutidOne(),onePaper.getAutidTwo(),onePaper.getAutidThree());
        //paper information
        Institute institute = instituteMapper.getInstituteById(authorMapper.getAuthorById(onePaper.getAutidOne()).getInsId());
        PaperDetail paperDetail = new PaperDetail();
        paperDetail.absorb(onePaper,authorMapper.getAuthorById(onePaper.getAutidOne()),institute);
        //encapsulate
        DataPandA dataPandA = new DataPandA(paperDetail,authorDetails);

        return Result.succ(dataPandA);
    }



    //发表管理”界面首页（作者）-论文添加
    /**
     * For author insertPaper
     */
    //改啦！！GY

    @ResponseBody
    @RequestMapping(value = "/author/paperAdd", method =  RequestMethod.POST)
    public Object insertPaper(@RequestBody PaperAddDto paperAddDto, @RequestParam("file") MultipartFile mpFile){
        JSONObject jsonObject = new JSONObject();
        /**
         * The paper_id is set as an Integer generated by the front side
         */
        Integer paper_id = paperAddDto.getPaper_id();
        Integer autid_One = paperAddDto.getAutid_one();
//        String paperDetail =  request.getParameter("paperDetail").trim();
        String pname = paperAddDto.getPname();
        String pabstract = paperAddDto.getPabstract();
        String keyword =  paperAddDto.getKeyword();
        String category = paperAddDto.getCategory();
        String file_url = paperAddDto.getFile_url();
        Integer autid_two = paperAddDto.getAutid_two();
        Integer autid_three = paperAddDto.getAutid_three();



        //Upload Paper File
        if(mpFile.isEmpty()){
            jsonObject.put("code", 0);
            jsonObject.put("message", "File Uploaded failed");
            return jsonObject;
        }
        //The name of file = The current time + the original file name
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        //File Path
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "paper";
        //If the file path not exists, create the file path
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //The real file location
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //Save the file into databases according to the relative file location
        String storeUrlPath = "/paper/" + fileName;
        try{
            mpFile.transferTo(dest);
            Paper paper = new Paper();
            paper.setPaperId(paper_id);
            paper.setAutidOne(autid_One);
            paper.setPname(pname);
            paper.setPabstract(pabstract);
            paper.setKeyWord(keyword);
            paper.setCategory(category);
            paper.setFileUrl(file_url);
            paper.setAutidTwo(autid_two);
            paper.setAutidThree(autid_three);
            boolean res = paperService.insertPaper(paper);
            boolean alloRev = paperService.reviewerAllocate(paper);

            if (res) {
                jsonObject.put("code", 400);
                jsonObject.put("paper", storeUrlPath);
                if(alloRev){
                    jsonObject.put("msg", "File Updated Successfully; Allocate reviewer Successfully");
                }
                else {
                    jsonObject.put("msg", "File Updated Successfully; Allocate reviewer Failed");
                }
                return jsonObject;
            } else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "File Uploaded Failed");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "File Uploaded Failed" + e.getMessage());
            return jsonObject;
        } finally {
            return jsonObject;

        }
    }




    @RequestMapping(value = "/author/paperEdit", method =  RequestMethod.POST)
    public Object updatePaper(@RequestBody PaperEditDto paperEditDto){
        JSONObject jsonObject = new JSONObject();
        /**
         * The paper_id is set as an Integer generated by the front side
         */
        Integer paper_id = paperEditDto.getPaper_id();
        Integer autid_One = paperEditDto.getAutid();
//        String paperDetail =  request.getParameter("paperDetail").trim();
        String pname = paperEditDto.getPname();
        String pabstract = paperEditDto.getPabstract();
        String keyword =  paperEditDto.getKeyword();
        String category = paperEditDto.getCategory();
        String file_url = paperEditDto.getFile_url();


        Paper paper = new Paper();
        paper.setPaperId(paper_id);
        paper.setAutidOne(autid_One);
        paper.setPname(pname);
        paper.setPabstract(pabstract);
        paper.setKeyWord(keyword);
        paper.setCategory(category);
        paper.setFileUrl(file_url);
        boolean res = paperService.updatePaper(paper);
        if (res) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "File Updated Successfully");
            return jsonObject;
        }
        else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "File Updated Failed");
            return jsonObject;
        }
    }







}
