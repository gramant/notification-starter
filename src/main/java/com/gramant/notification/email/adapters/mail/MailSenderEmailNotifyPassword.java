package com.gramant.notification.email.adapters.mail;

import com.gramant.notification.email.app.EmailNotifyPassword;
import com.gramant.notification.email.domain.Email;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailSender;

@AllArgsConstructor
public class MailSenderEmailNotifyPassword implements EmailNotifyPassword {

    private final MailSender mailSender;

    @Override
    public void requestPasswordChangeConfirmation(Email email, String token) {

    }

    @Override
    public void notifyPasswordChangeSucceeded(Email email) {

    }

    @Override
    public void notifyPasswordResetSucceeded(Email email, String newPassword) {

    }
}
