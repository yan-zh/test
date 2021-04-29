package com.example.demo.mapper;

import com.example.demo.entity.normal.Author;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.PaperDetail.AuthorDetail;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-22
 */
public interface AuthorMapper extends BaseMapper<Author> {

    Author getAuthorById(Integer id);

    List<AuthorDetail> getAuthorDetail(Integer one, Integer two, Integer three);

    List<Author> getAuthorByName(String name);
}
