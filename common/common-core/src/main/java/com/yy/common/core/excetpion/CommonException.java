package com.yy.common.core.excetpion;

import lombok.Getter;

/**
 * @author YY
 * @date 2020/1/3
 * @description
 */
@Getter
public class CommonException extends RuntimeException{


    private static final long serialVersionUID = -6211458259622321588L;
    private String msg;
    private Integer code;

    public CommonException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CommonException() {
        super();
    }

    public CommonException(String message) {
        super(message);
        this.msg = message;
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.msg = message;
    }

}
