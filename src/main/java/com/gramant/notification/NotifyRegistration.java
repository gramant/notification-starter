package com.gramant.notification;

public interface NotifyRegistration<T> {

    void requestRegistrationConfirmation(T address, String token);

    void notifyConfirmationSucceeded(T address);

    void notifyRegistrationSucceeded(T address);
}
