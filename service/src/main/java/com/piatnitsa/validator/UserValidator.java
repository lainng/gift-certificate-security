package com.piatnitsa.validator;

import com.piatnitsa.entity.User;
import com.piatnitsa.exception.ExceptionMessageHolder;
import com.piatnitsa.exception.ExceptionMessageKey;

public class UserValidator {
    private static final int MAX_EMAIL_LENGTH = 64;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 64;
    private static final String EMAIL_REGEXP = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";

    public static ExceptionMessageHolder validate(User user) {
        ExceptionMessageHolder holder = new ExceptionMessageHolder();
        holder.putAll(
                validateEmail(user.getEmail()).getMessages()
        );
        validatePassword(user.getPassword(), holder);
        validateName(user.getName(), holder);
        return holder;
    }

    public static ExceptionMessageHolder validateEmail(String email) {
        ExceptionMessageHolder holder = new ExceptionMessageHolder();
        if (email == null || email.isEmpty()
                || email.length() > MAX_EMAIL_LENGTH
                || !email.matches(EMAIL_REGEXP)) {
            holder.putException(ExceptionMessageKey.BAD_EMAIL, email);
        }
        return holder;
    }

    private static void validatePassword(String password, ExceptionMessageHolder holder) {
        if (password == null
                || password.length() < MIN_PASSWORD_LENGTH
                || password.length() > MAX_PASSWORD_LENGTH) {
            holder.putException(ExceptionMessageKey.BAD_USER_PASSWORD);
        }
    }

    private static void validateName(String name, ExceptionMessageHolder holder) {
        if (name == null
                || name.length() < MIN_NAME_LENGTH
                || name.length() > MAX_NAME_LENGTH) {
            holder.putException(ExceptionMessageKey.BAD_USER_NAME);
        }
    }
}
