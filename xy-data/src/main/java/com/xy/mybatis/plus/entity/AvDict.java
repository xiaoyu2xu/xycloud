package com.xy.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@TableName(value = "t_av_dict")
@Data
@Builder
public class AvDict {

    private Long id;

    private String avKey;

    private String avValue;
}
