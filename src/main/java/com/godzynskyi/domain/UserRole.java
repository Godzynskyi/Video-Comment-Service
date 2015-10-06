package com.godzynskyi.domain;

import javax.persistence.*;

/**
 * Created by Java Developer on 30.09.2015.
 */
@Entity
@Table(name = "user_roles")
public class UserRole {
    public static final int GUEST_ROLE = 0;
    public static final int USER_ROLE = 1;
    public static final int ADMIN_ROLE = 2;

    public UserRole(int role, User userId) {
        this.role = role;
        this.user = userId;
    }

    public UserRole() {
    }

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column
    private int role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRole() {
        return role;
    }

    public void setRole(short role) {
        this.role = role;
    }
}
