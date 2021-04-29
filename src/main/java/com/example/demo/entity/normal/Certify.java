package com.example.demo.entity.normal;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Certify implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer cerId;

    private Integer uniId;

    private Integer papId;

    private Integer revOne;

    private Integer revTwo;

    private Integer revThree;

    private String commonOne;

    private String commonTwo;

    private String commonThree;

    private String publicId;


}
