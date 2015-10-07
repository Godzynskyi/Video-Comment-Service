package com.godzynskyi.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
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

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_owner", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "document")
    private List<UserDocumentCredential> credentials = new ArrayList<UserDocumentCredential>();

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<Comment> comments = new LinkedList<Comment>();

    @Column(nullable = false)
    private Status status;

    public Document(String title, User owner, Status status) {
        this.title = title;
        this.owner = owner;
        this.status = status;
    }

    public Document() {
    }

    public List<Comment> getComments() {
        return comments;
    }

    public long getId() {
        return id;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
