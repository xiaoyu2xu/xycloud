package com.xy.exception;

import com.xy.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@NoArgsConstructor
public class XyException extends Exception {

    private int errCode;

    private String errMsg;

    private Throwable causeThrowable;

    public String getErrMsg() {
        if (!StringUtils.isBlank(this.errMsg)) {
            return errMsg;
        }
        if (this.causeThrowable != null) {
            return causeThrowable.getMessage();
        }
        return Constants.NULL;
    }

    public XyException(int errCode, String errMsg, Throwable causeThrowable) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.causeThrowable = causeThrowable;
    }

    public XyException(String message, int errCode, String errMsg, Throwable causeThrowable) {
        super(message);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.causeThrowable = causeThrowable;
    }

    public XyException(String message, Throwable cause, int errCode, String errMsg, Throwable causeThrowable) {
        super(message, cause);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.causeThrowable = causeThrowable;
    }

    public XyException(Throwable cause, int errCode, String errMsg, Throwable causeThrowable) {
        super(cause);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.causeThrowable = causeThrowable;
    }

    public XyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errCode, String errMsg, Throwable causeThrowable) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.causeThrowable = causeThrowable;
    }
}
