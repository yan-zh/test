package com.example.demo.entity.normal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * this entity store data of each papers
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer paperId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date pCreateTime;

    private String pname;

    private String keyWord;

    private String category;

    private String pabstract;

    private String biname;

    private Integer autidOne;

    private Integer autidTwo;

    private Integer autidThree;

    private String fileUrl;

    private Integer download;



}
