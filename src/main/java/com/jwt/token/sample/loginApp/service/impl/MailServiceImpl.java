package com.jwt.token.sample.loginApp.service.impl;

import com.jwt.token.sample.loginApp.exception.JmsEmailException;
import com.jwt.token.sample.loginApp.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    @Value("${mail.no-reply}")
    private String NO_REPLY;

    @Value("${mail.from}")
    private String REPLY_FROM;

    @Value("${mail.name}")
    private String REPLY_NAME;

    private final JavaMailSender javaMailSender;

    @Override
    public void send(String recipient, String subject, String content) throws JMSException {
        send(recipient, subject, content, false);
    }

    @Override
    public void send(String recipient, String subject, String context, boolean noReply) throws JMSException {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            buildEmailDetails(singletonList(recipient), subject, context, Collections.emptySet(), mimeMessage, noReply);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new JmsEmailException(e);
        }
    }

    private void buildEmailDetails(List<String> recipients,
                                   String subject,
                                   String content,
                                   Set<String> attachment,
                                   MimeMessage mimeMessage,
                                   boolean noReply) throws MessagingException, UnsupportedEncodingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        if (recipients.size() > 1) {
            helper.setTo(recipients.toArray(new String[0]));
        } else {
            helper.setTo(recipients.get(0));
        }
        helper.setSubject(subject);
        helper.setText(content, true);

        if (noReply) {
            helper.setFrom(NO_REPLY);
        } else {
            helper.setReplyTo(REPLY_FROM, REPLY_NAME);
            helper.setFrom(REPLY_FROM, REPLY_NAME);
        }

        List<String> attachmentNames = new ArrayList<>();
        attachment.forEach(filePaths -> {
            FileSystemResource emailAttachment = new FileSystemResource(filePaths);

            if (emailAttachment.exists()) {
                try {
                    String filename = emailAttachment.getFilename();
                    assert filename != null;

                    helper.addAttachment(filename, emailAttachment);
                    attachmentNames.add(filename);
                } catch (MessagingException e) {
                    log.warn(e.getMessage());
                }
            }
        });

        String logMessage = new StringBuilder()
                .append("\nDate: ")
                .append(LocalDateTime.now())
                .append("\nFrom: ")
                .append(noReply ? NO_REPLY : REPLY_FROM)
                .append("\nReply-to: ")
                .append(noReply ? NO_REPLY : REPLY_FROM)
                .append("\nto: ")
                .append(String.join(", ", recipients))
                .append("\nContent: ")
                .append(content)
                .append("\n\nAttachment: ")
                .append(buildAttachmentLogMessageInfo(attachmentNames))
                .append("\n-- END -- ")
                .toString();

        log.info(logMessage);
    }

    private String buildAttachmentLogMessageInfo(List<String> names) {
        if (names.isEmpty()) {
            return "NONE";
        } else {
            return String.format("%d files (%s)", names.size(), String.join(", ", names));
        }
    }

}
