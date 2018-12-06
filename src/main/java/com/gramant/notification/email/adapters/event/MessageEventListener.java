package com.gramant.notification.email.adapters.event;

import com.gramant.auth.domain.event.UsersMessaged;
import com.gramant.notification.UserId;
import com.gramant.notification.email.app.CallEmailAction;
import com.gramant.notification.email.app.EmailNotify;
import com.gramant.notification.email.domain.Email;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.function.Consumer;

@AllArgsConstructor
public class MessageEventListener {

    private final EmailNotify emailNotify;
    private final CallEmailAction emailAction;


    @EventListener
    @Async
    public void processUsersMessagedEvent(UsersMessaged event) {
        UserId[] userIds = event.users().stream().map(u -> u.id().asString()).map(UserId::of).toArray(UserId[]::new);
        Consumer<Email> emailConsumer = email -> emailNotify.notify(email, event.message());

        emailAction.email(emailConsumer, userIds);
    }
}
