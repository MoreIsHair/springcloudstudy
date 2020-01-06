package com.yy.common.log.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 描述
     *
     * @return {String}
     */
    String value();
}
