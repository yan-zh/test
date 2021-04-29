package com.example.demo.entity.normal;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "aut_id", type = IdType.AUTO)
    private Integer autId;

    private String aname;

    private Integer insId;

    private String email;

    private String tel;


    public String getAnameSpace(){
        if(aname==null)return " ";
        return aname;
    }

}
