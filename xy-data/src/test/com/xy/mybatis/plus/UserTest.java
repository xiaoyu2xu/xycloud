package com.xy.mybatis.plus;

import com.xy.common.RestResult;
import com.xy.mybatis.plus.entity.AdminUser;
import com.xy.mybatis.plus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- select userService method test ------"));
        AdminUser user = AdminUser.builder()
                .loginName("admin")
                .password("123456").build();
        RestResult<String> restResult = userService.queryUserInfo(user);
        System.out.println(restResult);
    }
}
