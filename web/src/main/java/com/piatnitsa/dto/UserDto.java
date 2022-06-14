package com.piatnitsa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.piatnitsa.entity.Role;
import org.springframework.hateoas.CollectionModel;

import java.util.Objects;

/**
 * This class represents data transfer object of {@link com.piatnitsa.entity.User} entity.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class UserDto extends CollectionModel<UserDto> {
    private long id;
    private String email;
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDto dto = (UserDto) o;
        return id == dto.id && Objects.equals(email, dto.email)
                && Objects.equals(name, dto.name)
                && Objects.equals(password, dto.password)
                && role == dto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, email, name, password, role);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDto{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
