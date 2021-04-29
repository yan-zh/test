package com.example.demo.service.impl;

import com.example.demo.entity.normal.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean verifyPassword(String username, String password) {
        return userMapper.verifyPassword(username,password)>0;
    }

    /**
     * The service of getting userInfo
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User userInfo(String username, String password) {
        return userMapper.userInfo(username, password);
    }

    /**
     * To insert a new user
     *
     * @param user
     * @return
     */
    @Override
    public boolean insertUser(User user) {
        return userMapper.insertUser(user) > 0;
    }


    /**
     *
     * @param user
     * @param ins_id
     * @return
     */
    @Override
    public boolean insertAuthor(User user) {
        return userMapper.insertAuthor(user) > 0;
    }

    /**
     * To insert a reviewer
     *
     * @param user
     * @return
     */
    @Override
    public boolean insertReviewer(User user) {
        return userMapper.insertReviewer(user) > 0;

    }

}
