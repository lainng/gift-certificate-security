package com.piatnitsa.validator;

import com.piatnitsa.entity.Role;
import com.piatnitsa.entity.User;
import com.piatnitsa.exception.ExceptionMessageHolder;
import com.piatnitsa.exception.ExceptionMessageKey;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class UserValidatorTest {
    private static final String CORRECT_EMAIL = "correct_email@email.com";
    private static final String INCORRECT_EMAIL = "incorrectEmail@email.comcom";
    private static final String CORRECT_NAME = "Jack Sparrow";
    private static final String INCORRECT_NAME = "M";
    private static final String CORRECT_PASSWORD = "StrongPassword";
    private static final String INCORRECT_PASSWORD = "weak";

    private final User CORRECT_USER = new User(1, CORRECT_NAME,
            CORRECT_EMAIL, CORRECT_PASSWORD, Role.USER);
    private final User INCORRECT_USER = new User(1, INCORRECT_NAME,
            INCORRECT_EMAIL, INCORRECT_PASSWORD, Role.USER);

    @Test
    void validateCorrectUser_thenOk() {
        ExceptionMessageHolder holder = UserValidator.validate(CORRECT_USER);
        assertFalse(holder.hasMessages());
    }

    @Test
    void validateIncorrectUser_thenThrowEx() {
        ExceptionMessageHolder holder = UserValidator.validate(INCORRECT_USER);
        assertTrue(holder.hasMessages());

        Map<String, Object[]> messages = holder.getMessages();
        assertTrue(messages.containsKey(ExceptionMessageKey.BAD_USER_EMAIL));
        assertTrue(messages.containsKey(ExceptionMessageKey.BAD_USER_NAME));
        assertTrue(messages.containsKey(ExceptionMessageKey.BAD_USER_PASSWORD));
    }

    @Test
    void validateCorrectEmail_thenOk() {
        ExceptionMessageHolder holder = UserValidator.validateEmail(CORRECT_EMAIL);
        assertFalse(holder.hasMessages());
    }


    @Test
    void validateIncorrectEmail_thenThrowEx() {
        ExceptionMessageHolder holder = UserValidator.validateEmail(INCORRECT_EMAIL);
        assertTrue(holder.hasMessages());

        Map<String, Object[]> messages = holder.getMessages();
        assertTrue(messages.containsKey(ExceptionMessageKey.BAD_USER_EMAIL));
    }
}