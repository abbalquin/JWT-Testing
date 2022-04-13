package com.jwt.token.sample.loginApp.service.impl;

import com.jwt.token.sample.loginApp.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JmsTemplate jmsTemplate;
    private static final String SAMPLE_QUEUE="sample.queue2";

    @Override
    public boolean sendUserNotification(Map<String, String> message) {
        return this.sendMessage(SAMPLE_QUEUE, message);
    }

    @Async
    private boolean sendMessage(String queue,Map<String, String> message){
        try{
            jmsTemplate.convertAndSend(queue, message);
        } catch (Exception e) {
            log.warn("Message sending failed " + message, e);
            return false;
        }
        return true;
    }
}
