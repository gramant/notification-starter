package com.gramant.notification.email.app;

import com.gramant.notification.UserId;
import com.gramant.notification.email.domain.Email;
import com.gramant.notification.email.domain.UserEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

public interface CallEmailAction {

    void email(Consumer<Email> emailAction, UserId... userIds);


    // default implementation
    @Slf4j
    @AllArgsConstructor
    class Default implements CallEmailAction {

        private final ProvideEmail provideEmail;

        @Override
        public void email(Consumer<Email> emailAction, UserId... userIds) {
            Collection<UserEmail> userEmails;
            if (userIds != null && userIds.length > 0) {
                userEmails = provideEmail.emailsOf(userIds);
                userEmails.stream()
                        .filter(userEmail -> userEmail.email().isPresent())
                        .map(UserEmail::email)
                        .map(Optional::get)
                        .forEach(emailAction);
                userEmails.stream()
                        .filter(userEmail -> !userEmail.email().isPresent())
                        .map(UserEmail::userId)
                        .forEach(userNotExistsConsumer());
            }
        }

        private static Consumer<UserId> userNotExistsConsumer() {
            return userId -> log.debug("Email for user [" + userId.asString() + "] not exist!");
        }
    }
}
