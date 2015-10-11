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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(nullable = false, name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<UserDocumentCredential> credentials = new ArrayList<UserDocumentCredential>();

    @OneToMany(mappedBy = "document", cascade = CascadeType.REMOVE)
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public User getOwner() {
        return owner;
    }
}
