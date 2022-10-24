package com.john.service.impl;

import com.john.enums.Sex;
import com.john.mapper.UsersMapper;
import com.john.pojo.Users;
import com.john.pojo.bo.UserBo;
import com.john.service.UserService;
import com.john.utils.DateUtil;
import com.john.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author johnconstantine
 */
@Service
public class UserServiceImpl implements UserService {

    private final UsersMapper usersMapper;
    private final Sid sid;

    public UserServiceImpl(UsersMapper usersMapper, Sid sid) {
        this.usersMapper = usersMapper;
        this.sid = sid;
    }

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public boolean queryUserNameIsExist(String username) {
        Users users = usersMapper.selectByUserName(username);
        return users != null;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Users createUser(UserBo userBo) {
        Users users = new Users();
        users.setId(this.sid.nextShort());
        users.setUsername(userBo.getUsername());
        try {
            users.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 默认用户昵称同用户名
        users.setNickname(userBo.getUsername());
        // 默认头像
        users.setFace(USER_FACE);
        // 默认生日
        users.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // 默认性别为 保密
        users.setSex(Sex.secret.type);

        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());

        usersMapper.insert(users);
        return users;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Users queryUserForLogin(String username, String password) {
        UserBo userBo = new UserBo();
        userBo.setUsername(username);
        try {
            userBo.setPassword(MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*通过名字和密码验证，目前密码查询有问题，所以先用名字查询对应的用户，因为不可以同名，所以不会查询出两条数据的usersMapper.selectByUser(userBo);*/
        return usersMapper.selectByUserName(username);
    }
}
