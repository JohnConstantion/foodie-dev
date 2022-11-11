package com.john.service;

import com.john.pojo.Category;

import java.util.List;

/**
 * @author johnconstantine
 */
public interface CategoryService {

    /**
     * 查询所有一级分类
     *
     * @return {@link List<Category>}
     */
    List<Category> queryAllRootLevelCat();
}
