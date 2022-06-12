package com.piatnitsa.validator;

import com.piatnitsa.exception.ExceptionMessageHolder;
import com.piatnitsa.exception.ExceptionMessageKey;

public class UserValidator {

    private static final String EMAIL_REGEXP = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";

    public static ExceptionMessageHolder validateEmail(String email) {
        ExceptionMessageHolder holder = new ExceptionMessageHolder();
        if (email == null
                || email.isEmpty()
                || !email.matches(EMAIL_REGEXP)) {
            holder.putException(ExceptionMessageKey.BAD_EMAIL, email);
        }
        return holder;
    }
}
