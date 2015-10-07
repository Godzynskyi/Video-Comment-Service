package com.godzynskyi.dao;

import com.godzynskyi.domain.Comment;

import java.util.List;

/**
 * Created by Java Developer on 07.10.2015.
 */
public interface CommentDAO {

    boolean addComment(Comment comment);
    Comment getComment(long id);

    List<Comment> getCommentsFromIndex(long documentId, int index);
    boolean changeComment(Comment comment);
    boolean removeComment(Comment comment);

}
