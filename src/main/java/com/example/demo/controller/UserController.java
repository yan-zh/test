package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.normal.Institute;
import com.example.demo.entity.normal.University;
import com.example.demo.entity.normal.User;
import com.example.demo.lang.Result;
import com.example.demo.mapper.InstituteMapper;
import com.example.demo.mapper.UniversityMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-19
 */
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UniversityMapper universityMapper;

    @Autowired
    InstituteMapper instituteMapper;

    @Autowired
    private UserService userService;

//    @PostMapping("/login")
//    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
//
//        //User user = userService.getOne(new QueryWrapper<User>().eq("uname", loginDto.getUsername()));
//        System.out.println(loginDto.getUsername());
//        User user = userMapper.getUserByUName(loginDto.getUsername());
//        Assert.notNull(user, "用户不存在");
//
//        System.out.println(loginDto.getPassword());
//        System.out.println(user.getPassword());
//
//        if(!user.getPassword().equals(loginDto.getPassword())){
//            return Result.fail("密码不正确");
//        }
//        String jwt = jwtUtils.generateToken(user.getUserId());
//
//        response.setHeader("Authorization", jwt);
//        response.setHeader("Access-control-Expose-Headers", "Authorization");
//
//        return Result.succ(MapUtil.builder()
//                .put("user_id", user.getUserId())
//                .put("uname", user.getUname())
//                .put("rname", user.getRname())
//                .map()
//        );
//    }



    //登录
    /**
     * verify whether the login is successful
     */
    //这里接口冲突
    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public Object loginStatus(HttpServletRequest request){
    public Object loginStatus(@RequestBody LoginDto loginDto){

            JSONObject jsonObject = new JSONObject();
//        String name = request.getParameter("username");
//        String password = request.getParameter("password");
        String name = loginDto.getUsername();
        String password = loginDto.getPassword();
        boolean flag = userService.verifyPassword(name,password);
        if(flag){
            jsonObject.put("code",400);
            jsonObject.put("message","Login Successfully");
            User user = userService.userInfo(name, password);
//            session.setAttribute("name",name);
            jsonObject.put("username", user.getUname());
            jsonObject.put("lastname", user.getLastname());
            jsonObject.put("firstname", user.getFirstname());
            jsonObject.put("rtype", user.getRname());
            jsonObject.put("aut_id", user.getAutId());
            jsonObject.put("rev_id", user.getRevId());
            //return jsonObject;
            return Result.succ(jsonObject);

        }
        jsonObject.put("code",0);
        jsonObject.put("message","Wrong user name or password");
        return jsonObject;
    }


//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public Object loginStatus(String username, String password){
//        JSONObject jsonObject = new JSONObject();
//
//        System.out.println(username + password);
//        boolean flag = userService.verifyPassword(username,password);
//        if(flag){
//            jsonObject.put("code",400);
//            jsonObject.put("message","Login Successfully");
//            User user = userService.userInfo(username, password);
//            jsonObject.put("username", user.getUname());
//            jsonObject.put("lastname", user.getLastname());
//            jsonObject.put("firstname", user.getFirstname());
//            jsonObject.put("rtype", user.getRname());
//            jsonObject.put("aut_id", user.getAutId());
//            jsonObject.put("rev_id", user.getRevId());
//            return jsonObject;
//        }
//        jsonObject.put("code",0);
//        jsonObject.put("message","Wrong user name or password");
//        return jsonObject;
//    }

//    //注册
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public Object addUser(HttpServletRequest req){
//        JSONObject jsonObject = new JSONObject();
//        String username = req.getParameter("username").trim();
//        String password = req.getParameter("password").trim();
//        String rname = req.getParameter("rname").trim();
//        String rtype = req.getParameter("rtype").trim();
//        String firstname = req.getParameter("firstname").trim();
//        String lastname = req.getParameter("lastname").trim();
//
//        User user = new User();
//        user.setUname(username);
//        user.setPassword(password);
//        user.setRname(rname);
//        user.setRkey(rtype);
//        user.setFirstname(firstname);
//        user.setLastname(lastname);
//
//        boolean res = userService.insertUser(user);
//        if (res){
//            jsonObject.put("code", 400);
//            jsonObject.put("msg", "Register successfully");
//            return jsonObject;
//        }else {
//            jsonObject.put("code", 0);
//            jsonObject.put("msg", "Register failed");
//            return jsonObject;
//        }
//    }


    /**
     * To register
     * @param registerDto
     * @return
     */

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object addUser(@RequestBody RegisterDto registerDto){
        JSONObject jsonObject = new JSONObject();

        String username = registerDto.getUname();
        String password = registerDto.getPassword();
        String lastname  = registerDto.getLastname();
        String firstname = registerDto.getFirstname();
        String email = registerDto.getEmail();
        String tel = registerDto.getTel();
        String rtype = registerDto.getRname();
        String category = registerDto.getCategory();
        String universityID = registerDto.getUniversityID();
        String instituteID = registerDto.getInstituteID();


        User user = new User();
        user.setUname(username);
        user.setPassword(password);
        user.setRname(rtype);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setTel(tel);
        user.setCategory(category);
        user.setUniversityID(Integer.parseInt(universityID));
        user.setInstituteID(Integer.parseInt(instituteID));
        boolean res = userService.insertUser(user);
        System.out.println("Rname got: " + user.getRname());

        if (res){
            //A author B reviewer
            if(user.getRname().equals("A"))
            {
                boolean res_aut = userService.insertAuthor(user);
                System.out.println(res_aut);
                if(res_aut)
                {
                    jsonObject.put("code", 400);
                    jsonObject.put("msg", "Author Register Successfully");
                    return jsonObject;
                }
                else {
                    jsonObject.put("code", 0);
                    jsonObject.put("msg", "Author Register Failed");
                    return jsonObject;
                }
            }
            if(user.getRname().equals("B"))
            {
                boolean res_rev = userService.insertReviewer(user);
                if(res_rev)
                {
                    jsonObject.put("code", 400);
                    jsonObject.put("msg", "Reviewer Register successfully");
                    return jsonObject;
                }
                else {
                    jsonObject.put("code", 0);
                    jsonObject.put("msg", "Author Register Failed");
                    return jsonObject;
                }
            }
            else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "Rtype needed");
                return jsonObject;
            }
        }
        else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "Register Failed");
            return jsonObject;
        }
    }


    //大学列表拉取
    @GetMapping("/register/uni")//这里以后要修改url
    //要接收一个searchwords
    public Result loginListUniversity(String searchWords){
        University university = universityMapper.getUniversityByName(searchWords);
        Integer universityId = university.getUniId();
        return Result.succ(universityId);
    }



    //学院下拉列表
    @GetMapping("/register/ins")
    public Result loginListInstitute(String institute, Integer universityID){
        Institute theInstitute = instituteMapper.getInstituteByNameAndUniId(institute,universityID);
        Integer instituteId = theInstitute.getInsId();
        return Result.succ(instituteId);
    }



}
