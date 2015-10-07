package com.godzynskyi.service;

import com.godzynskyi.domain.Comment;
import com.godzynskyi.domain.Document;

import java.util.List;

/**
 * Created by Java Developer on 07.10.2015.
 */
public interface CommentService {

    // insert Comment in position of index
    boolean createComment(Comment comment);

    Comment getComment(long id);

    boolean removeComment(Comment comment);
    boolean changeComment(Comment comment);
}
