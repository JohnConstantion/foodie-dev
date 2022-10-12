package com.john.service;

import com.john.pojo.Stu;

/**
 * @author johnconstantine
 */
public interface StuService {
    /**
     * 查询用户信息
     *
     * @param id ID
     * @return {@link Stu}
     */
    Stu getStuInfo(int id);

    /**
     * 插入用户信息
     */
    void insert();

    /**
     * 更新用户信息
     *
     * @param id ID
     */
    void updateById(int id);

    /**
     * 删除用户信息
     *
     * @param id ID
     */
    void deleteById(int id);
}
