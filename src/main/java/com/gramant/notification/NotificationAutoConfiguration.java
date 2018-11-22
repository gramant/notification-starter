package com.gramant.notification;

import com.gramant.notification.email.adapters.mail.MailSenderEmailNotify;
import com.gramant.notification.email.adapters.mail.MailSenderEmailNotifyPassword;
import com.gramant.notification.email.adapters.mail.MailSenderEmailNotifyRegistration;
import com.gramant.notification.email.app.EmailNotify;
import com.gramant.notification.email.app.EmailNotifyPassword;
import com.gramant.notification.email.app.EmailNotifyRegistration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
@ConditionalOnBean(MailSender.class)
@AutoConfigureAfter({MailSenderAutoConfiguration.class})
public class NotificationAutoConfiguration {

    @Bean
    public EmailNotify emailNotify(MailSender mailSender) {
        return new MailSenderEmailNotify(mailSender);
    }

    @Bean
    public EmailNotifyRegistration emailNotifyRegistration(MailSender mailSender) {
        return new MailSenderEmailNotifyRegistration(mailSender);
    }

    @Bean
    public EmailNotifyPassword emailNotifyPassword(MailSender mailSender) {
        return new MailSenderEmailNotifyPassword(mailSender);
    }
}
