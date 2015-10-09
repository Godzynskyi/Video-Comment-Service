package com.godzynskyi.dao;

import com.godzynskyi.SpringContext;
import com.godzynskyi.domain.User;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by Java Developer on 30.09.2015.
 */
public class UserDAOTest {

//    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringContext.class);
//
//    IUserDAO userDAO = new SpringContext().userDao();
//
//    @Test
//    public void testAddUser() {
//        User user = new User("ivan", "asdf");
//        userDAO.addUser(user);
//        boolean getIsAdded = userDAO.addUser(new User("ivan", "qwer"));
//        assertFalse(getIsAdded);
//    }
//
//    @Test
//    public void testGetUser() {
//        userDAO.addUser(new User("ivan", "asdf"));
//        User user = userDAO.getUser("ivan");
//        assertEquals(user.getPassword(), "asdf");
//    }

}
