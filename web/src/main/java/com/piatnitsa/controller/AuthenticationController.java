package com.piatnitsa.controller;

import com.piatnitsa.dto.UserCredentialsDto;
import com.piatnitsa.dto.UserDto;
import com.piatnitsa.dto.converter.DtoConverter;
import com.piatnitsa.entity.User;
import com.piatnitsa.hateoas.LinkBuilder;
import com.piatnitsa.jwt.JWTProvider;
import com.piatnitsa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final DtoConverter<UserDto, User> userDtoConverter;
    private final LinkBuilder<UserDto> linkBuilder;

    @Autowired
    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager,
                                    JWTProvider jwtProvider, DtoConverter<UserDto, User> userDtoConverter,
                                    LinkBuilder<UserDto> linkBuilder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userDtoConverter = userDtoConverter;
        this.linkBuilder = linkBuilder;
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public UserCredentialsDto authorizeUser(@RequestBody UserCredentialsDto userCredentialsDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userCredentialsDto.getEmail(), userCredentialsDto.getPassword()));
        String token = jwtProvider.generateToken(userCredentialsDto.getEmail());
        userCredentialsDto.setToken(token);
        return userCredentialsDto;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@RequestBody UserDto userDto) {
        User savedUser = userService.insert(userDtoConverter.toEntity(userDto));
        UserDto savedUserDto = userDtoConverter.toDto(savedUser);
        linkBuilder.buildLinks(savedUserDto);
        return savedUserDto;
    }
}
