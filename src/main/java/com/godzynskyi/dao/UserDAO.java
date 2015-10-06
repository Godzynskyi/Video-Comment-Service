package com.godzynskyi.dao;

import com.godzynskyi.SpringContext;
import com.godzynskyi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Java Developer on 30.09.2015.
 */
@Component
public class UserDAO implements IUserDAO{
    @Autowired
    EntityManager em /*= new SpringContext().entityManager()*/;


    @Override
    public boolean addUser(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public User getUser(String login) {
        Query query = em.createQuery("select a from User a where a.login = \'" + login + "\'", User.class);
        List<User> user = (List<User>) query.getResultList();
        if(user.size()==0) return null;
        if(user.size()!=1) throw new RuntimeException("There more than one user with this login in Database");
        return user.get(0);
    }

    @Override
    public User getUser(long id) {
        return em.find(User.class, id);
    }

}
