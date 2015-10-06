package com.godzynskyi.service;

import com.godzynskyi.domain.Document;
import com.godzynskyi.domain.User;
import com.godzynskyi.validation.AddUserStatus;

import java.util.List;

/**
 * Created by Java Developer on 04.10.2015.
 */
public interface IUserService {

    boolean isAuthorized(String login, String password);

    AddUserStatus addNewUser(User user);

    User getUser(String login);
    User getUser(long id);
}
