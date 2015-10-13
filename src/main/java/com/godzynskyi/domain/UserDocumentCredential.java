package com.godzynskyi.domain;

import javax.persistence.*;

/**
 * Created by Java Developer on 03.10.2015.
 */
@Entity
@Table(name="user_document")
public class UserDocumentCredential {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="id_user")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="id_document")
    private Document document;

    @Column
    private Credentials credentials;

    public Document getDocument() {
        return document;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public User getUser() {
        return user;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public UserDocumentCredential(User user, Document document, Credentials credentials) {
        this.user = user;
        this.document = document;
        this.credentials = credentials;
    }

    public UserDocumentCredential() {
    }
}
