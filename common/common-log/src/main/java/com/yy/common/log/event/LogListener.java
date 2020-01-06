package com.yy.common.log.event;

import com.yy.common.core.model.Log;
import com.yy.user.api.feign.UserServiceClient;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 异步监听日志事件
 */
public class LogListener {

    private UserServiceClient userServiceClient;

    public LogListener(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    /**
     * 异步记录日志
     *
     * @param event event
     */
    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveSysLog(LogEvent event) {
        Log log = (Log) event.getSource();
        userServiceClient.saveLog(log);
    }
}
