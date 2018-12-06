package com.gramant.notification.email.domain;

import com.gramant.notification.UserId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Optional;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
@ToString(of = {"userId", "email"})
public final class UserEmail {

    private final UserId userId;
    private final Optional<Email> email;
}
