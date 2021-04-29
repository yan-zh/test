package com.example.demo.entity.normal;

import com.baomidou.mybatisplus.annotation.IdType;

import java.sql.Date;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String uname;

    private String password;

    private String lastname;

    private String firstname;

    private String sex;

    private String nation;

    private Date birthday;

    private String idCardNum;

    private String email;

    private String tel;

    private String image;

    private Integer autId;

    private Integer revId;

    private String rname;

    private String rkey;

    //多出来的三个，不在数据库里
    private String category;

    private Integer universityID;

    private Integer instituteID;


}
