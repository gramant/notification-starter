package com.gramant.notification;

import com.gramant.notification.email.adapters.mail.EmailMessageSender;
import com.gramant.notification.email.adapters.mail.MailSenderEmailNotify;
import com.gramant.notification.email.adapters.mail.MailSenderEmailNotifyPassword;
import com.gramant.notification.email.adapters.mail.MailSenderEmailNotifyRegistration;
import com.gramant.notification.email.app.EmailNotify;
import com.gramant.notification.email.app.EmailNotifyPassword;
import com.gramant.notification.email.app.EmailNotifyRegistration;
import com.gramant.notification.email.app.EmailProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public EmailProvider emailProvider() {
        return new EmailProvider.Default();
    }
}
