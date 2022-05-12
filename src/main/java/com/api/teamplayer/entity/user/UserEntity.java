package com.api.teamplayer.entity.user;

import com.api.teamplayer.entity.base.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
public class UserEntity extends BaseEntity {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getName() {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(this.firstName)) {
            sb.append(this.firstName);
            sb.append(" ");
        }
        if (!StringUtils.isEmpty(this.lastName)) {
            sb.append(this.lastName);
            sb.append(" ");
        }
        this.name = sb.toString().trim();
        return name;
    }
}
