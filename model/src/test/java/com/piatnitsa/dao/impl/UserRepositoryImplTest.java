package com.piatnitsa.dao.impl;

import com.piatnitsa.config.DaoTestConfig;
import com.piatnitsa.dao.UserRepository;
import com.piatnitsa.entity.Role;
import com.piatnitsa.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = DaoTestConfig.class)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
@Transactional
class UserRepositoryImplTest {
    private static final long NOT_EXISTED_ID = 999L;
    private final User USER_1 = new User(1, "name1", "email1@email.com",
            "$2a$12$uZ8GTbHV019Cfq1QuSR0xeEpsp6cse3s41E0r6BnLgpEJdEUdB6y2", Role.USER);
    private final User USER_2 = new User(2, "name2", "email2@email.com",
            "$2a$12$lwRYdasb8dKItMgwFPVd2u26C1s3kNySJSdT1abAPBhTKOLGdO1Gy", Role.USER);
    private final Pageable pageRequest = PageRequest.of(0, 5);

    @Autowired
    UserRepository userRepository;

    @Test
    void getById_thenOk() {
        Optional<User> expected = Optional.of(USER_1);
        Optional<User> actual = userRepository.findById(USER_1.getId());
        assertEquals(expected, actual);
    }

    @Test
    void getByNotExistedId_thenReturnNull() {
        Optional<User> actual = userRepository.findById(NOT_EXISTED_ID);
        assertFalse(actual.isPresent());
    }

    @Test
    void getAll_thenOk() {
        List<User> expected = Arrays.asList(USER_1, USER_2);
        List<User> actual = userRepository.findAll(pageRequest).toList();
        assertEquals(expected, actual);
    }
}