package com.piatnitsa.dto;

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
                && role == dto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, email, name, role);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDto{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
