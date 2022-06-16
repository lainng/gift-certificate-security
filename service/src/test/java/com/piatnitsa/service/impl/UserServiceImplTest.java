package com.piatnitsa.service.impl;

import com.piatnitsa.dao.UserRepository;
import com.piatnitsa.entity.Role;
import com.piatnitsa.entity.User;
import com.piatnitsa.exception.NoSuchEntityException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private static final long NOT_EXISTED_ID = 999L;
    private static final int PAGE = 0;
    private static final int SIZE = 5;

    private final User USER_1 = new User(1, "name1", "email1@email.com",
            "$2a$12$uZ8GTbHV019Cfq1QuSR0xeEpsp6cse3s41E0r6BnLgpEJdEUdB6y2", Role.USER);
    private final User USER_2 = new User(2, "name2", "email2@email.com",
            "$2a$12$lwRYdasb8dKItMgwFPVd2u26C1s3kNySJSdT1abAPBhTKOLGdO1Gy", Role.USER);
    private final User USER_3 = new User(3, "name3", "email3@email.com",
            "$2a$12$A9GM81nIwG4ZnbBSthoa3elAumwQMLnkO245UrniNh1HCXaFqamca", Role.USER);

    @Mock UserRepository userRepository;
    @InjectMocks UserServiceImpl userService;

    @Test
    void getById_thenOk() {
        Mockito.when(userRepository.findById(USER_1.getId())).thenReturn(Optional.of(USER_1));
        User actual = userService.getById(USER_1.getId());
        assertEquals(USER_1, actual);
    }

    @Test
    void getNotExistedById_thenThrowEx() {
        Mockito.when(userRepository.findById(NOT_EXISTED_ID)).thenReturn(Optional.empty());
        assertThrows(NoSuchEntityException.class, () -> userService.getById(NOT_EXISTED_ID));
    }

    @Test
    void getAll_thenOk() {
        List<User> expected = Arrays.asList(USER_1, USER_2, USER_3);
        Pageable pageRequest = PageRequest.of(PAGE, SIZE);
        Mockito.when(userRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(expected));

        List<User> actual = userService.getAll(PAGE, SIZE);
        assertEquals(expected, actual);
    }

}