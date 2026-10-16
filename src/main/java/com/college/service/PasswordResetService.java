package com.college.service;

import com.college.core.entity.PasswordResetToken;
import com.college.core.entity.User;
import com.college.repository.PasswordResetTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/** Checks the token of a password-reset link. */
@Service
public class PasswordResetService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetService(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    /**
     * Result of {@link #checkToken}: {@code error} is {@code null} for a valid token, otherwise the message key suffix
     * ({@code invalidToken} or {@code expired}); {@code user} is set only for a valid token.
     */
    public record TokenCheck(String error, User user) {
    }

    public TokenCheck checkToken(long userId, String token) {
        PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
        if (passToken == null || passToken.getUser().getId() != userId) {
            return new TokenCheck("invalidToken", null);
        }
        Calendar cal = Calendar.getInstance();
        if (passToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0) {
            return new TokenCheck("expired", null);
        }
        return new TokenCheck(null, passToken.getUser());
    }
}
