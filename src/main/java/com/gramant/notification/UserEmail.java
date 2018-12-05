package com.gramant.notification;

import com.gramant.notification.email.domain.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
@ToString(of = {"userId", "email"})
public class UserEmail {

    private final UserId userId;
    private final Email email;
}
