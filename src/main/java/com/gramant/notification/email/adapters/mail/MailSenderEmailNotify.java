package com.gramant.notification.email.adapters.mail;

import com.gramant.notification.email.app.EmailNotify;
import com.gramant.notification.email.domain.Email;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailSender;

@AllArgsConstructor
public class MailSenderEmailNotify implements EmailNotify {

    private final MailSender mailSender;

    @Override
    public void notify(Email email, String message) {

    }
}
