package com.john.service;

import com.john.pojo.Category;
import com.john.pojo.vo.CategoryVO;

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

    /**
     * 根据一级分类ID查询子分类的信息
     *
     * @param rootCatId 一级分类ID
     * @return 子分类的信息 {@link List<CategoryVO>}
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);
}
