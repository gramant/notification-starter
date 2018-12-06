package com.gramant.notification.email.adapters.jdbc;

import com.gramant.notification.UserId;
import com.gramant.notification.email.app.ProvideEmail;
import com.gramant.notification.email.domain.Email;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class JdbcProvideEmail implements ProvideEmail {

    // todo: make configurable
    private static final String CONTACT_TABLE = "user_contact";
    private static final String CONTACT_ID_COLUMN = "user_id";
    private static final String CONTACT_EMAIL_COLUMN = "email";

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Optional<Email> emailOf(UserId userId) {
        String query = String.format("SELECT %s FROM %s WHERE %s = ?", CONTACT_EMAIL_COLUMN, CONTACT_TABLE, CONTACT_ID_COLUMN);
        List<String> emails = jdbcTemplate.queryForList(query, new Object[] {userId.asString()}, String.class);
        return emails.isEmpty() ? Optional.empty() : Optional.of(Email.of(emails.get(0)));
    }
}
