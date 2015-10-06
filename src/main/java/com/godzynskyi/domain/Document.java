package com.godzynskyi.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Java Developer on 03.10.2015.
 */
@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_owner")
    private User owner;

    @OneToMany(mappedBy = "document")
    private List<UserDocumentCredential> credentials;

    @OneToMany(mappedBy = "document")
    private List<Comment> comments;

    @Column
    private Status status;
}
