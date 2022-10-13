package com.john.service;

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
}
