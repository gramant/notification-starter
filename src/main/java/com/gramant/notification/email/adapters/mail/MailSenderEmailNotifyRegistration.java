package com.gramant.notification.email.adapters.mail;

import com.gramant.notification.email.app.EmailNotifyRegistration;
import com.gramant.notification.email.domain.Email;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailSender;

@AllArgsConstructor
public class MailSenderEmailNotifyRegistration implements EmailNotifyRegistration {

    private final MailSender mailSender;

    @Override
    public void requestRegistrationConfirmation(Email email, String token) {

    }

    @Override
    public void notifyConfirmationSucceeded(Email email) {

    }

    @Override
    public void notifyRegistrationSucceeded(Email email) {

    }
}
