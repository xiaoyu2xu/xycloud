package com.xy.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName(value = "t_admin_user")
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {

    private long id;

    private String loginName;

    private String password;
}
