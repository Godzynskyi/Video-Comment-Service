package com.godzynskyi.dao;

import com.godzynskyi.domain.Credentials;
import com.godzynskyi.domain.Document;
import com.godzynskyi.domain.User;
import com.godzynskyi.domain.UserDocumentCredential;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Java Developer on 11.10.2015.
 */
@Repository
public class UserDocumentCredentialsDAOImpl implements UserDocumentCredentialsDAO {

    private static final Logger logger = Logger.getLogger(UserDocumentCredentialsDAOImpl.class);
    @Autowired
    private EntityManager em;

    @Override
    public Credentials getCredentials(long userId, long docId) {
        Query query = em.createQuery("select c.credentials from UserDocumentCredential c " +
                "where c.user.id=:userId and c.document.id=:docId");
        query.setParameter("userId", userId);
        query.setParameter("docId", docId);
        List<Credentials> list = (List<Credentials>) query.getResultList();
        if(list.isEmpty()) {
            return null;
        }
        if(list.size()>1) logger.error("There more than one credentials for this document from this user.");
        return list.get(0);
    }

    @Override
    public UserDocumentCredential getCredentials(User user, Document document) {
        Query query = em.createQuery("select c from UserDocumentCredential c " +
                "where c.user.id=:userId and c.document.id=:docId");
        query.setParameter("userId", user.getId());
        query.setParameter("docId", document.getId());
        List<UserDocumentCredential> list = (List<UserDocumentCredential>) query.getResultList();
        if(list.isEmpty()) return null;
        if(list.size()>1) logger.error("There more than one credentials for this document from this user.");
        return list.get(0);
    }

    @Override
    public List<UserDocumentCredential> getCredentials(User user) {
        Query query = em.createQuery("select c from UserDocumentCredential c " +
                "where c.user.id=:userId");
        query.setParameter("userId", user.getId());
        List<UserDocumentCredential> list = (List<UserDocumentCredential>) query.getResultList();

        return list;
    }

    @Override
    public List<UserDocumentCredential> getCredentials(Document document) {
        Query query = em.createQuery("select c from UserDocumentCredential c where c.document.id=:docId");
        query.setParameter("docId", document.getId());
        List<UserDocumentCredential> list = (List<UserDocumentCredential>) query.getResultList();
        return list;
    }

    @Override
    public boolean addCredentials(UserDocumentCredential credentials) {
        try {
            em.getTransaction().begin();
            em.persist(credentials);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean update(UserDocumentCredential credentials) {
        try {
            em.getTransaction().begin();
            em.merge(credentials);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean delete(UserDocumentCredential userDocumentCredential) {
        try {
            em.getTransaction().begin();
            em.remove(userDocumentCredential);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        }
    }
}
