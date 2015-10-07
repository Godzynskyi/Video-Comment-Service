package com.godzynskyi;

import com.godzynskyi.dao.UserDAO;
import com.godzynskyi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Java Developer on 30.09.2015.
 */
@Component
public class Hello {

    @Autowired
    UserDAO userDAO;


    public boolean testAddUser() {
        User user = new User("ivan", "asdf");
        userDAO.addUser(user);
        boolean isAdded = userDAO.addUser(new User("ivan", "qwer"));
        return isAdded;
    }

//    public boolean testGetUser() {
//        userDAO.addUser(new User("ivan", "asdf", (short) 1));
//        User user = userDAO.getUserByLogin("ivan");
//        return "asdf".equals(user.getPassword());
//    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
        config.register(SpringContext.class);
        config.refresh();

        Hello h = new Hello();
        h.userDAO = new SpringContext().userDAO();


        System.out.println(h.testAddUser());
//        System.out.println(h.testGetUser());
    }
}
