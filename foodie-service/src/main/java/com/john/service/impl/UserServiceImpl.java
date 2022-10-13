package com.john.service.impl;

import com.john.mapper.UsersMapper;
import com.john.pojo.Users;
import com.john.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author johnconstantine
 */
@Service
public class UserServiceImpl implements UserService {

    private final UsersMapper usersMapper;

    public UserServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public boolean queryUserNameIsExist(String username) {
        Users users = usersMapper.selectByUserName(username);
        return users != null;
    }
}
