package com.godzynskyi.service;

import com.godzynskyi.dao.CommentDAO;
import com.godzynskyi.domain.Comment;
import com.godzynskyi.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Java Developer on 07.10.2015.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public boolean createComment(Comment comment, int position) {
            return commentDAO.addComment(comment, position);
    }

    @Override
    public Comment getComment(long id) {
        return commentDAO.getComment(id);
    }

    @Override
    public boolean changeComment(Comment comment) {
        return commentDAO.changeComment(comment);
    }

    @Override
    public boolean removeComment(Comment comment) {
        return commentDAO.removeComment(comment);
    }

    @Override
    public List<Comment> getComments(long docId) {

        return commentDAO.getComments(docId);
    }
}
