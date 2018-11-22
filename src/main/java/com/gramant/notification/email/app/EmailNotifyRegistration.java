package com.gramant.notification.email.app;

import com.gramant.notification.NotifyRegistration;
import com.gramant.notification.email.domain.Email;

public interface EmailNotifyRegistration extends NotifyRegistration<Email> {

    void requestRegistrationConfirmation(Email email, String token);

    void notifyConfirmationSucceeded(Email email);

    void notifyRegistrationSucceeded(Email email);
}
