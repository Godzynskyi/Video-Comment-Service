package com.godzynskyi.domain;

import javax.persistence.*;

/**
 * Created by Java Developer on 03.10.2015.
 */
@Entity
@Table(name="comment")
public class Comment implements Comparable<Comment> {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_document", nullable = false)
    private Document document;

    @ManyToOne
    @JoinColumn(name = "id_video", nullable = false)
    private Video video;

    //index of comment for sorting comments in document
    @Column
    private int index;

    @Column(nullable = false)
    private String comment;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_time")
    private int startTime;

    @Column(name = "end_time")
    private Integer endTime;

    @Column
    private String style;

    public Comment(Document document, Video video, String comment) {
        this.document = document;
        this.video = video;
        this.comment = comment;
    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public Document getDocument() {
        return document;
    }

    public Video getVideo() {
        return video;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }


    public int getIndex() {
        return index;
    }

    public void incrementIndex() {
        index++;
    }

    public void decrementIndex() {
        index--;
    }

    @Override
    public int compareTo(Comment o) {
        return o.index - this.index;
    }
}
