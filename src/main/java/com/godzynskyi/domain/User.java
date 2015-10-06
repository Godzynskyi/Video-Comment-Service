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

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRole> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Document> userDocuments;


    @OneToMany(mappedBy = "user")
    private List<UserDocumentCredential> credentials;

    public User(String login, String password, int... role) {
        this.login = login;
        this.password = password;

        roles = new ArrayList<UserRole>(2);
        if(role.length == 0) roles.add(new UserRole(1, this)); else for(int i: role) roles.add(new UserRole(i, this));
    }


    public User() {}

    public long getId() {
        return id;
    }

    public List<Document> getUserDocuments() {
        return userDocuments;
    }

    public void setUserDocuments(List<Document> userDocuments) {
        this.userDocuments = userDocuments;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }
}
