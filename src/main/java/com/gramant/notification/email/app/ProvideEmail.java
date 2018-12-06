package com.gramant.notification.email.app;

import com.gramant.notification.UserId;
import com.gramant.notification.email.domain.Email;
import com.gramant.notification.email.domain.UserEmail;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public interface ProvideEmail {

    Optional<Email> emailOf(UserId userId);

    default Collection<UserEmail> emailsOf(UserId... userIds) {
        return Stream.of(userIds).map(userId -> new UserEmail(userId, emailOf(userId))).collect(toList());
    }
}
