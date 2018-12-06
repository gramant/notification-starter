package com.gramant.notification.email.adapters.event;

import com.gramant.auth.app.HandleRegistrationEvent;
import com.gramant.auth.domain.event.EmailConfirmationCompleted;
import com.gramant.auth.domain.event.EmailConfirmationRequested;
import com.gramant.notification.UserId;
import com.gramant.notification.email.app.CallEmailAction;
import com.gramant.notification.email.app.EmailNotifyRegistration;
import com.gramant.notification.email.domain.Email;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.function.Consumer;

@AllArgsConstructor
public class RegistrationEventListener implements HandleRegistrationEvent {

    private final EmailNotifyRegistration emailNotifyRegistration;
    private final CallEmailAction emailAction;

    @Override
    @EventListener
    @Async
    public void processEmailConfirmationRequestedEvent(EmailConfirmationRequested event) {
        UserId userId = UserId.of(event.token().userId().asString());
        Consumer<Email> emailConsumer = email -> emailNotifyRegistration.requestRegistrationConfirmation(email, event.token().tokenId().asString());
        emailAction.email(emailConsumer, userId);
    }

    @Override
    @EventListener
    @Async
    public void processEmailConfirmationCompletedEvent(EmailConfirmationCompleted event) {
        UserId userId = UserId.of(event.user().id().asString());
        Consumer<Email> emailConsumer = email -> emailNotifyRegistration.notifyConfirmationSucceeded(email);
        emailAction.email(emailConsumer, userId);
    }
}
