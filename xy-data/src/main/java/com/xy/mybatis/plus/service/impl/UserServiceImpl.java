package com.xy.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xy.common.Constants;
import com.xy.common.RestResult;
import com.xy.exception.XyException;
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
        if(result == null){
            RestResult.success("message is null");
        }
        return RestResult.success(Constants.CODE_SUCCESS_MESSAGE);
    }

    @Override
    public RestResult<String> insertUserInfo(AdminUser adminUser) {
        Integer result = userMapper.insert(adminUser);
        if(result > 0){
            return RestResult.success("insertUserInfo success! adminUser = " + adminUser);
        }
        return RestResult.error(XyException.builder().errCode(Constants.INSERT_CODE_ERROR).errMsg(Constants.INSERT_CODE_ERROR_MESSAGE).build());
    }
}
