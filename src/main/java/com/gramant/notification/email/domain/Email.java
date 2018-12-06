package com.gramant.notification.email.domain;

import java.io.Serializable;
import java.util.Objects;

public final class Email implements Serializable {

    private final String value;

    public static Email of(String value) {
        return new Email(value);
    }

    private Email(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public String asString() {
        return value;
    }
}
