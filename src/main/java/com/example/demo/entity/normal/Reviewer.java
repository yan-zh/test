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
 * @since 2021-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Reviewer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rev_id", type = IdType.AUTO)
    private Integer revId;

    private String position;

    private String revEmail;

    private Integer insId;


}
