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
    private static final String SUCCESS_MESSAGE = "调用成功";

    private int code;
    private String message;
    private T data;

    public RestResult error(XyException xyException){
        return RestResult.builder().code(xyException.getErrCode())
                .message(xyException.getErrMsg()).build();
    }

    public <T> RestResult<T> success(T data){
        return RestResult.<T>builder().code(SUCCESS_CODE)
                .message(SUCCESS_MESSAGE).data(data).build();
    }
}
