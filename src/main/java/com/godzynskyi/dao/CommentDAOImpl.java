package com.godzynskyi.dao;

import com.godzynskyi.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Java Developer on 07.10.2015.
 */
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    EntityManager em;

    @Override
    public boolean addComment(Comment comment) {
        try{
            em.getTransaction().begin();
            em.persist(comment);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Comment getComment(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> getCommentsFromIndex(long documentId, int index) {
        Query query = em.createQuery("select c from Comment c where c.document = " + documentId + "and c.index >= " + index);
        return query.getResultList();
    }

    @Override
    public boolean changeComment(Comment comment) {
        try {
            em.getTransaction().begin();
            em.merge(comment);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean removeComment(Comment comment) {
        try {
            em.getTransaction().begin();
            em.remove(comment);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        }
    }
}
