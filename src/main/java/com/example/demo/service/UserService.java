package com.example.demo.service;

import com.example.demo.entity.normal.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-19
 */
public interface UserService extends IService<User> {
    /**
     * The service of verifying password
     * @param username
     * @param password
     * @return
     */
    public boolean verifyPassword(String username, String password);

    /**
     * The service of getting userInfo
     * @param username
     * @param password
     * @return
     */
    public User userInfo(String username, String password);

    /**
     * To insert a new user
     * @param user
     * @return
     */
    public boolean insertUser(User user);


    /**
     * To insert a new Author
     * @param user
     * @return
     */
    public boolean insertAuthor(User user);

    /**
     *To insert a reviewer
     * @param user
     * @return
     */
    public boolean insertReviewer(User user);
}
