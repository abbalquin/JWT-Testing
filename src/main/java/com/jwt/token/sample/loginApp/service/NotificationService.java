package com.jwt.token.sample.loginApp.service;

import java.util.Map;

public interface NotificationService {

    boolean sendUserNotification(Map<String, String> message);
}
