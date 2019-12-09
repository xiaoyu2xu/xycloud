package com.xy.common;

import com.xy.exception.XyException;
import lombok.Builder;
import lombok.Data;

/**
 * @author zhangxy
 * @describe TODO
 * @date 2019年12月04日 9:16
 */
@Data
@Builder
public class RestResult<T> {

    private static final int SUCCESS_CODE = 1;
    private static final String SUCESS_MESSAGE = "调用成功";

    private int code;
    private String message;
    private T data;

    public static RestResult error(XyException xyException){
        return RestResult.builder().code(xyException.getErrCode())
                .message(xyException.getErrMsg()).build();
    }

    public static <T> RestResult<T> sucess(T data){
        return RestResult.<T>builder().code(SUCCESS_CODE)
                .message(SUCESS_MESSAGE).data(data).build();
    }
}
