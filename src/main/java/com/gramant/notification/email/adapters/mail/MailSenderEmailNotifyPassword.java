package com.gramant.notification.email.adapters.mail;

import com.gramant.notification.email.app.EmailNotifyPassword;
import com.gramant.notification.email.domain.Email;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MailSenderEmailNotifyPassword implements EmailNotifyPassword {

    private final EmailMessageSender emailMessageSender;

    @Override
    public void requestPasswordChangeConfirmation(Email email, String token) {
        emailMessageSender.sendMessage(email.asString(), "Confirm password change", token);
    }

    @Override
    public void notifyPasswordChangeSucceeded(Email email) {
        emailMessageSender.sendMessage(email.asString(), "Password changed", "");
    }

    @Override
    public void notifyPasswordResetSucceeded(Email email, String newPassword) {
        emailMessageSender.sendMessage(email.asString(), "Password reset", newPassword);
    }
}
