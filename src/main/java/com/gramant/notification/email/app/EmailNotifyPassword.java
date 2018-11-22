package com.gramant.notification.email.app;

import com.gramant.notification.NotifyPassword;
import com.gramant.notification.email.domain.Email;

public interface EmailNotifyPassword extends NotifyPassword<Email> {

    void requestPasswordChangeConfirmation(Email email, String token);

    void notifyPasswordChangeSucceeded(Email email);

    void notifyPasswordResetSucceeded(Email email, String newPassword);
}
