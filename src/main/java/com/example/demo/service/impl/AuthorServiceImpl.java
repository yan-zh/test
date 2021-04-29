package com.example.demo.service.impl;

import com.example.demo.entity.normal.Author;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.service.AuthorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-22
 */
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {

}
