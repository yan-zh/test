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
 * @since 2021-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class University implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uni_id", type = IdType.AUTO)
    private Integer uniId;

    private String uname;

    private String nation;

    private String city;

    private String address;

    private Date birthday;

    private String tel;

    private String principal;


}
