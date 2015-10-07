package com.godzynskyi.service;

import com.godzynskyi.dao.UserDAO;
import com.godzynskyi.domain.User;
import com.godzynskyi.validation.AddUserStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by Java Developer on 04.10.2015.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    EntityManager em /*= new SpringContext().entityManager()*/;
    @Autowired
    UserDAO userDAO /*= new SpringContext().userDao()*/;

    @Override
    public boolean isAuthorized(String login, String password) {
        User user = userDAO.getUser(login);
        if(user == null) return false;
        String pass = user.getPassword();
        if(!pass.equals(password)) return false;
        return true;
    }

    @Override
    public AddUserStatus addNewUser(User user) {
        AddUserStatus res = new AddUserStatus();
        if(getUser(user.getLogin())!=null) {
            res.setNotUserExist(false);
            return res;
        }
        res.setAdded(userDAO.addUser(user));
        return res;
    }

    @Override
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    @Override
    public User getUser(long id) {
        return getUser(id);
    }
}
