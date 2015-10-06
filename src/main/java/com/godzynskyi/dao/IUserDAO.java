package com.godzynskyi.dao;

import com.godzynskyi.domain.User;

/**
 * Created by Java Developer on 30.09.2015.
 */
public interface IUserDAO {
    boolean addUser(User user);
    User getUser(String login);
    User getUser(long id);
}
