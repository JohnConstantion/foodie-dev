package com.john.mapper;

import com.john.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    List<Category> selectByType(Integer type);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}