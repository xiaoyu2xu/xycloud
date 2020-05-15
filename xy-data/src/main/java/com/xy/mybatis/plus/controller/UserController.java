package com.xy.mybatis.plus.controller;

import com.xy.common.RestResult;
import com.xy.mybatis.plus.entity.AdminUser;
import com.xy.mybatis.plus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public RestResult<String> login(@ModelAttribute AdminUser adminUser){
        RestResult<String> restResult = userService.queryUserInfo(adminUser);
        return restResult;
    }
}
