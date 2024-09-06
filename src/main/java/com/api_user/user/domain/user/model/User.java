package com.api_user.user.domain.user.model;

import com.api_user.user.domain.role.model.Role;

import java.time.LocalDate;

public class User {

    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String phone;
    private LocalDate birthdate;
    private String email;
    private String password;
    private Role role;

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastname = builder.lastname;
        this.dni = builder.dni;
        this.phone = builder.phone;
        this.birthdate = builder.birthdate;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String lastname;
        private String dni;
        private String phone;
        private LocalDate birthdate;
        private String email;
        private String password;
        private Role role;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder birthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
