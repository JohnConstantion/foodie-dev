package com.john.service;

import com.john.pojo.Users;
import com.john.pojo.bo.UserBo;

/**
 * @author johnconstantine
 */
public interface UserService {

    /**
     * 验证名字是否冲突
     *
     * @param username username
     * @return boolean
     */
    boolean queryUserNameIsExist(String username);

    /**
     * 创建用户信息
     *
     * @param userBo 页面用户信息
     * @return {@link UserBo}
     */
    Users createUser(UserBo userBo);
}
