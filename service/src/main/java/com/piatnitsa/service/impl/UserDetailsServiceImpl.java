package com.piatnitsa.service.impl;

import com.piatnitsa.dao.UserRepository;
import com.piatnitsa.entity.User;
import com.piatnitsa.exception.ExceptionMessageHolder;
import com.piatnitsa.exception.ExceptionMessageKey;
import com.piatnitsa.exception.IncorrectParameterException;
import com.piatnitsa.exception.NoSuchEntityException;
import com.piatnitsa.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ExceptionMessageHolder holder = UserValidator.validateEmail(email);
        if (holder.hasMessages()) {
            throw new IncorrectParameterException(holder);
        }

        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new NoSuchEntityException(ExceptionMessageKey.USER_NOT_FOUND);
        }

        User user = optionalUser.get();
        List<GrantedAuthority> userAuthorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole().toString())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                userAuthorities
        );
    }
}
