package com.gramant.notification;

public interface Notify<T> {

    void notify(T address, String message);
}
