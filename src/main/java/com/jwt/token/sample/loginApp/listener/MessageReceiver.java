package com.jwt.token.sample.loginApp.listener;

import com.jwt.token.sample.loginApp.constant.EmailTemplate;
import com.jwt.token.sample.loginApp.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.jms.JMSException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MessageReceiver {

    private final MailService mailService;
    private final TemplateEngine templateEngine;

    @JmsListener(destination = "sample.queue")
    public void userNotification() {
        System.out.println("Hello User Notification 1");
    }

    @JmsListener(destination = "${active-mq.notification.user}")
    public void userNotification2(Map<String, String> message) throws JMSException {
        message.put("emailAddress", "abbalquin@gmail.com");
        mailService.send(message.get("emailAddress"), EmailTemplate.SAMPLE_TEMPLATE.getSubject(),
                templateEngine.process(EmailTemplate.SAMPLE_TEMPLATE.getTemplatePath(), buildContext(message)));
    }

    private Context buildContext(Map<String, String> message) {
        Context context = new Context();
        context.setVariable("targetUser", message.get("PASSWORD"));
        return context;
    }
}
