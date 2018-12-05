package com.gramant.notification.email.adapters.mail;

import com.gramant.notification.email.app.EmailNotify;
import com.gramant.notification.email.domain.Email;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MailSenderEmailNotify implements EmailNotify {

    private final EmailMessageSender emailMessageSender;

    @Override
    public void notify(Email email, String message) {
        emailMessageSender.sendMessage(email.asString(), "Notification", message);
    }
}
