package com.godzynskyi.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Java Developer on 30.09.2015.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long id;

    @Column
    private String login;

    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "UserRoles")
    private List<Short> roles;

    public User(String login, String password, Short... role) {
        this.login = login;
        this.password = password;

        roles = new ArrayList<Short>(2);
        if(role.length == 0) roles.add(UserRoles.USER_ROLE);
        for(Short i: role) roles.add(i);
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Short> getRoles() {
        return roles;
    }
}
