package com.api.teamplayer.dto.user;

public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;

    public static User init() {
        return new User();
    }

    public User userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public User firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public User build() {
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
