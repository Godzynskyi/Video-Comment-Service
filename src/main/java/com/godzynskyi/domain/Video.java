package com.godzynskyi.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Java Developer on 03.10.2015.
 */
@Entity
@Table(name="video")
public class Video {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "video")
    private List<Comment> comments;

    @Column(unique = true)
    private String url;

    public Video(String url) {
        this.url = url;
    }

    public Video() {
    }

    public String getUrl() {
        return url;
    }
}
