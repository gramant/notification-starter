package com.gramant.notification;

import com.gramant.auth.app.HandlePasswordEvent;
import com.gramant.auth.app.HandleRegistrationEvent;
import com.gramant.notification.email.adapters.event.MessageEventListener;
import com.gramant.notification.email.adapters.event.PasswordEventListener;
import com.gramant.notification.email.adapters.event.RegistrationEventListener;
import com.gramant.notification.email.adapters.jdbc.JdbcProvideEmail;
import com.gramant.notification.email.adapters.mail.EmailMessageSender;
import com.gramant.notification.email.adapters.mail.MailSenderEmailNotify;
import com.gramant.notification.email.adapters.mail.MailSenderEmailNotifyPassword;
import com.gramant.notification.email.adapters.mail.MailSenderEmailNotifyRegistration;
import com.gramant.notification.email.app.*;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.MailSender;

@Configuration
@ConditionalOnBean(MailSender.class)
@AutoConfigureAfter({MailSenderAutoConfiguration.class})
public class NotificationAutoConfiguration {

    @Bean
    public EmailMessageSender emailMessageSender(MailSender mailSender) {
        return new EmailMessageSender(mailSender);
    }

    @Bean
    @ConditionalOnMissingBean
    public EmailNotify emailNotify(EmailMessageSender emailMessageSender) {
        return new MailSenderEmailNotify(emailMessageSender);
    }

    @Bean
    @ConditionalOnMissingBean
    public EmailNotifyRegistration emailNotifyRegistration(EmailMessageSender emailMessageSender) {
        return new MailSenderEmailNotifyRegistration(emailMessageSender);
    }

    @Bean
    @ConditionalOnMissingBean
    public EmailNotifyPassword emailNotifyPassword(EmailMessageSender emailMessageSender) {
        return new MailSenderEmailNotifyPassword(emailMessageSender);
    }

    @Bean
    @ConditionalOnMissingBean
    public ProvideEmail emailProvider(JdbcTemplate jdbcTemplate) {
        return new JdbcProvideEmail(jdbcTemplate);
    }

    @Bean
    public CallEmailAction emailAction(ProvideEmail provideEmail) {
        return new CallEmailAction.Default(provideEmail);
    }

    @Bean
    @ConditionalOnMissingBean
    public HandlePasswordEvent handlePasswordEvent(EmailNotifyPassword emailNotifyPassword, CallEmailAction emailAction) {
        return new PasswordEventListener(emailNotifyPassword, emailAction);
    }

    @Bean
    @ConditionalOnMissingBean
    public HandleRegistrationEvent handleRegistrationEvent(EmailNotifyRegistration emailNotifyRegistration, CallEmailAction emailAction) {
        return new RegistrationEventListener(emailNotifyRegistration, emailAction);
    }

    @Bean
    public MessageEventListener messageEventListener(EmailNotify emailNotify, CallEmailAction emailAction) {
        return new MessageEventListener(emailNotify, emailAction);
    }
}
