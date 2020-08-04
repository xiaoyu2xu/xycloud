package com.xy.mybatis.plus.service;

import com.xy.common.RestResult;
import com.xy.mybatis.plus.entity.AdminUser;

public interface UserService{

    RestResult<String> queryUserInfo(AdminUser adminUser);

    RestResult<String> insertUserInfo(AdminUser adminUser);
}
