package com.john.mapper;

import com.john.pojo.Stu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author johnconstantine
 */
@Mapper
public interface StuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stu record);

    int insertSelective(Stu record);

    Stu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stu record);

    int updateByPrimaryKey(Stu record);
}