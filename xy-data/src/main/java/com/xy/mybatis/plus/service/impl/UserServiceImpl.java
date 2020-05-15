package com.xy.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xy.common.RestResult;
import com.xy.mybatis.plus.entity.AdminUser;
import com.xy.mybatis.plus.mapper.UserMapper;
import com.xy.mybatis.plus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public RestResult<String> queryUserInfo(AdminUser adminUser) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        AdminUser result = userMapper.selectOne(queryWrapper);
        RestResult<Object> restResut = RestResult.builder().build();
        if(result == null){
            restResut.success("the message is null");
        }
        return restResut.success("the message is right");
    }
}
