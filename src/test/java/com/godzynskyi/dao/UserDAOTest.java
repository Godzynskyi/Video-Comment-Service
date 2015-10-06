package com.godzynskyi.dao;

import com.godzynskyi.SpringContext;
import com.godzynskyi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by Java Developer on 30.09.2015.
 */
public class UserDAOTest {
    @Autowired
    IUserDAO userDAO;

//    {
//        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
//        config.register(SpringContext.class);
//        config.refresh();
//    }

//    public void testAddUser() {
//        User user = new User("ivan", "asdf", (short) 1);
//        userDAO.addUser(user);
//        boolean isAdded = userDAO.addUser(new User("ivan", "qwer"));
//        assertFalse(isAdded);
//    }
//
//    public void testGetUser() {
//        userDAO.addUser(new User("ivan", "asdf", (short) 1));
//        User user = userDAO.getUserByLogin("ivan");
//        assertEquals(user.getPassword(), "asdf");
//    }

}
