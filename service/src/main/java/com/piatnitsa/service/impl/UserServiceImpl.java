package com.piatnitsa.service.impl;

import com.piatnitsa.dao.UserRepository;
import com.piatnitsa.entity.Role;
import com.piatnitsa.entity.User;
import com.piatnitsa.exception.ExceptionMessageHolder;
import com.piatnitsa.exception.IncorrectParameterException;
import com.piatnitsa.service.AbstractService;
import com.piatnitsa.service.UserService;
import com.piatnitsa.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User insert(User entity) {
        ExceptionMessageHolder holder = UserValidator.validate(entity);
        if (holder.hasMessages()) {
            throw new IncorrectParameterException(holder);
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setRole(Role.USER);
        return userRepository.save(entity);
    }

    @Override
    public void removeById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> doFilter(MultiValueMap<String, String> params, int page, int size) {
        throw new UnsupportedOperationException();
    }
}
