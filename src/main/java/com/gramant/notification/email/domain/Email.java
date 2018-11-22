package com.gramant.notification.email.domain;

import java.io.Serializable;

public class Email implements Serializable {

    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }
}
