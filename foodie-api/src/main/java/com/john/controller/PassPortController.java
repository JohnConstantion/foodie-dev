package com.john.controller;

import com.john.service.UserService;
import com.john.utils.IMOOCJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author johnconstantine
 */
@RestController
@RequestMapping("/passport")
public class PassPortController {

    private final UserService userService;

    public PassPortController(UserService userService) {
        this.userService = userService;
    }

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
}
