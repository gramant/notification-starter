package com.gramant.notification.email.adapters.event;

import com.gramant.auth.app.HandlePasswordEvent;
import com.gramant.auth.domain.event.PasswordChangeCompleted;
import com.gramant.auth.domain.event.PasswordChangeRequested;
import com.gramant.auth.domain.event.PasswordResetCompleted;
import com.gramant.notification.UserId;
import com.gramant.notification.email.app.CallEmailAction;
import com.gramant.notification.email.app.EmailNotifyPassword;
import com.gramant.notification.email.domain.Email;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.function.Consumer;

@AllArgsConstructor
public class PasswordEventListener implements HandlePasswordEvent {

    private final EmailNotifyPassword emailNotifyPassword;
    private final CallEmailAction emailAction;

    @Override
    @EventListener
    @Async
    public void handlePasswordChangeRequestedEvent(PasswordChangeRequested event) {
        UserId userId = UserId.of(event.verificationToken().userId().asString());
        Consumer<Email> emailConsumer = email -> emailNotifyPassword.requestPasswordChangeConfirmation(email, event.verificationToken().tokenId().asString());
        emailAction.email(emailConsumer, userId);
    }

    @Override
    @EventListener
    @Async
    public void handlePasswordChangeCompletedEvent(PasswordChangeCompleted event) {
        UserId userId = UserId.of(event.user().id().asString());
        Consumer<Email> emailConsumer = email -> emailNotifyPassword.notifyPasswordChangeSucceeded(email);
        emailAction.email(emailConsumer, userId);
    }

    @Override
    @EventListener
    @Async
    public void handlePasswordResetCompletedEvent(PasswordResetCompleted event) {
        UserId userId = UserId.of(event.user().id().asString());
        Consumer<Email> emailConsumer = email -> emailNotifyPassword.notifyPasswordResetSucceeded(email, event.newPassword());
        emailAction.email(emailConsumer, userId);
    }
}
