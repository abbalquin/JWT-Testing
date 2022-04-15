package com.jwt.token.sample.loginApp.exception;

import javax.jms.JMSException;

public class JmsEmailException extends JMSException {

    public JmsEmailException(Exception e){
        super(e.getMessage());
        setLinkedException(e);
    }
}
