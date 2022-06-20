package com.piatnitsa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents DTO that contains the user's credentials.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class UserCredentialsDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
