package com.john.controller;

import com.john.pojo.Users;
import com.john.pojo.bo.UserBo;
import com.john.service.UserService;
import com.john.utils.CookieUtils;
import com.john.utils.IMOOCJSONResult;
import com.john.utils.JsonUtils;
import com.john.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author johnconstantine
 */
@RestController
@Api(value = "注册登录", tags = "用于登录注册的相关接口")
@RequestMapping("/passport")
public class PassPortController {

    private final UserService userService;

    public PassPortController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "判断用户名是否存在", tags = "判断用户名是否存在", httpMethod = "GET")
    @GetMapping("/username_is_exist")
    public IMOOCJSONResult queryUserName(@RequestParam String username) {
        // 1、检验参数是否为空
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("用户名不可为空");
        }
        // 2、查询用户名是否存在
        boolean isExist = userService.queryUserNameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        // 3、请求成功，用户名没有重复
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户注册", tags = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response) {

        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();

        // 0、判断用的的名字和密码是否为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("用户名或者密码不可为空");
        }
        // 1、查询用户名是否存在
        if (userService.queryUserNameIsExist(username)) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        // 2、判断密码的长度是否超过6位数
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码的长度需要超过6位数");
        }
        // 3、判断密码和确认密码是否一致
        if (!password.equals(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("密码和验证密码不一致");
        }
        // 4、实现注册
        Users user = userService.createUser(userBo);
        setNullProperty(user);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        return IMOOCJSONResult.ok(user);
    }

    @ApiOperation(value = "用户登录", tags = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = userBo.getUsername();
        String password = userBo.getPassword();

        // 0、判断用的的名字和密码是否为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return IMOOCJSONResult.errorMsg("用户名或者密码不可为空");
        }

        // 1、判断密码的长度是否超过6位数
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码的长度需要超过6位数");
        }

        // 4、实现查询
        Users user = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        setNullProperty(user);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        return IMOOCJSONResult.ok(user);
    }

    private void setNullProperty(Users users) {
        users.setPassword(null);
        users.setCreatedTime(null);
        users.setUpdatedTime(null);
        users.setBirthday(null);
        users.setEmail(null);
        users.setMobile(null);
        users.setRealname(null);
    }
}
