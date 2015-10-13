package com.godzynskyi.dao;

import com.godzynskyi.domain.Comment;
import com.godzynskyi.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Java Developer on 07.10.2015.
 */
@Repository
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    EntityManager em;

    @Override
    public boolean addComment(Comment comment, int position) {
        try{
            comment.setIndex(position);
            em.getTransaction().begin();
            Query query = em.createQuery("update Comment c set c.index= c.index + 1 where c.index >= :position");
            query.setParameter("position",position);
            query.executeUpdate();
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

    @Override
    public List<Comment> getComments(long docId) {
        Query query = em.createQuery("select c from Comment c where c.document.id = :docId");
        query.setParameter("docId", docId);
        List<Comment> list = (List<Comment>) query.getResultList();
        for(Comment c: list) em.refresh(c);
        Collections.sort(list);
        return list;
    }


}
