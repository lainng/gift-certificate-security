package com.piatnitsa.service.impl;

import com.piatnitsa.exception.ExceptionMessageHolder;
import com.piatnitsa.exception.IncorrectParameterException;
import com.piatnitsa.validator.UserValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ExceptionMessageHolder holder = UserValidator.validateEmail(email);
        if (holder.hasMessages()) {
            throw new IncorrectParameterException(holder);
        }


        return null;
    }
}
