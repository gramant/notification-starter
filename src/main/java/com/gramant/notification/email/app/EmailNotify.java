package com.gramant.notification.email.app;

import com.gramant.notification.email.domain.Email;

public interface EmailNotify extends com.gramant.notification.Notify<Email> {

    void notify(Email email, String message);

}
