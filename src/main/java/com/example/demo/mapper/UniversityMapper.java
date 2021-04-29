package com.example.demo.mapper;

import com.example.demo.entity.normal.University;
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
public interface UniversityMapper extends BaseMapper<University> {
    University getUniversityByName(String likeName);
}
