package com.john.service.impl;

import com.john.mapper.CategoryMapper;
import com.john.pojo.Category;
import com.john.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author johnconstantine
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> queryAllRootLevelCat() {
        return categoryMapper.selectByType(1);
    }
}
