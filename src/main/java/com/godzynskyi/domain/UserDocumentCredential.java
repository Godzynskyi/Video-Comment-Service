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

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name="id_document")
    private Document document;

    @Column
    private Credentials credentials;
}
