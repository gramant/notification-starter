package com.gramant.notification;

public interface NotifyPassword<T> {

    void requestPasswordChangeConfirmation(T address, String token);

    void notifyPasswordChangeSucceeded(T address);

    void notifyPasswordResetSucceeded(T address, String newPassword);
}
