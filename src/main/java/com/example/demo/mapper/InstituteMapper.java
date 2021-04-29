package com.example.demo.mapper;

import com.example.demo.entity.normal.Institute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-22
 */
public interface InstituteMapper extends BaseMapper<Institute> {

    Institute getInstituteById(Integer id);

    List<Institute> getInstituteByVagueName(String likeName);

    Institute getInstituteByNameAndUniId(String likeName, Integer universityID);
}
