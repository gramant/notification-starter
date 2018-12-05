package com.gramant.notification.email.adapters.mail;

import com.gramant.notification.email.app.EmailNotifyRegistration;
import com.gramant.notification.email.domain.Email;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MailSenderEmailNotifyRegistration implements EmailNotifyRegistration {

    private final EmailMessageSender emailMessageSender;

    @Override
    public void requestRegistrationConfirmation(Email email, String token) {
        emailMessageSender.sendMessage(email.asString(), "Confirm registration", token);
    }

    @Override
    public void notifyConfirmationSucceeded(Email email) {
        emailMessageSender.sendMessage(email.asString(), "Confirmed registration", "");
    }

    @Override
    public void notifyRegistrationSucceeded(Email email) {
        emailMessageSender.sendMessage(email.asString(), "Registration completed", "");
    }
}
