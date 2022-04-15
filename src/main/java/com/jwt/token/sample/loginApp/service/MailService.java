package com.jwt.token.sample.loginApp.service;

import javax.jms.JMSException;

public interface MailService {

    void send(String recipient, String subject, String context) throws JMSException;

    void send(String recipient, String subject, String context, boolean noReply) throws JMSException;
}
