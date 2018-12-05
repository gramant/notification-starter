package com.gramant.notification.email.app;

import com.gramant.notification.UserEmail;
import com.gramant.notification.UserId;
import com.gramant.notification.email.domain.Email;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toMap;

public interface EmailProvider {

    Optional<Email> email(UserId userId);


    // default implementation
    class Default implements EmailProvider {

        private final Map<UserId, Email> mappedEmails;

        public Default(UserEmail... userEmails) {
            this.mappedEmails = Optional.ofNullable(userEmails).map(a -> Stream.of(a).collect(toMap(UserEmail::userId, UserEmail::email))).orElse(emptyMap());
        }

        @Override
        public Optional<Email> email(UserId userId) {
            return Optional.ofNullable(mappedEmails.get(userId));
        }
    }
}
