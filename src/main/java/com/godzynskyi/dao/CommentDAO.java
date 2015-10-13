package com.godzynskyi.dao;

import com.godzynskyi.domain.Comment;
import com.godzynskyi.domain.Document;

import java.util.List;

/**
 * Created by Java Developer on 07.10.2015.
 */
public interface CommentDAO {

    boolean addComment(Comment comment, int position);
    Comment getComment(long id);

    boolean changeComment(Comment comment);
    boolean removeComment(Comment comment);
    List<Comment> getComments(long docId);

}
