package com.example.demo.mapper;

import com.example.demo.entity.normal.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-19
 */
public interface UserMapper extends BaseMapper<User> {

    //User getUserByUName(String uname);

    User getUserByAutId(Integer id);


    /**
     *To Verify the password
     * @param username
     * @param password
     * @return
     */
    public int verifyPassword(String username, String password);

    /**
     *To get the information of User
     * @param username
     * @return
     */
    public User userInfo(String username, String password);

    /**
     * To insert a new User
     * @param user
     * @return
     */
    public int insertUser(User user);


    /**
     * To insert a data of author
     * @param user
     * @return
     */
    public int insertAuthor(User user);

    /**
     * To insert a data of reviewer
     * @param user
     * @return
     */
    public int insertReviewer(User user);

}
