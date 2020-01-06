package com.yy.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yy.common.security.serializer.CustomOauthExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author YY
 * @date 2020/1/3
 * @description
 */
@Getter
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

    private static final long serialVersionUID = -8411399997953823882L;
    private  Integer code;

    public CustomOauthException(String msg, Throwable t, Integer code) {
        super(msg, t);
        this.code = code;
    }

    public CustomOauthException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public CustomOauthException(String msg, Throwable t) {
        super(msg, t);
    }
}
