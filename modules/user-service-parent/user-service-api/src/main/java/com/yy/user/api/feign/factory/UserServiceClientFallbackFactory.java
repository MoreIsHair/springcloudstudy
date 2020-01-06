package com.yy.user.api.feign.factory;

import com.yy.user.api.feign.UserServiceClient;
import com.yy.user.api.feign.fallback.UserServiceClientFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author YY
 * @date 2020/1/6
 * @description
 */
@Component
public class UserServiceClientFallbackFactory implements FallbackFactory<UserServiceClient> {

    @Autowired
    UserServiceClientFallbackImpl userServiceClientFallback;

    @Override
    public UserServiceClient create(Throwable throwable) {
        // UserServiceClientFallbackImpl userServiceClientFallback = new UserServiceClientFallbackImpl();
        userServiceClientFallback.setThrowable(throwable);
        return userServiceClientFallback;
    }

}
