package com.john.mapper;

import com.john.pojo.Users;
import com.john.pojo.bo.UserBo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String id);

    Users selectByUserName(String username);

    Users selectByUser(UserBo userBo);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}