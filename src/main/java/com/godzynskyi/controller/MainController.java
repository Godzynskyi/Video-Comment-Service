package com.godzynskyi.controller;

import com.godzynskyi.domain.User;
import com.godzynskyi.service.UserService;
import com.godzynskyi.validation.AddUserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by Java Developer on 08.10.2015.
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser")
    public String addUser(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "role", required = false, defaultValue = "1") int role,
            Model model) {

        User myUser = new User(login, password, role);
        AddUserStatus addUserStatus = userService.addNewUser(myUser);
        model.addAttribute("addUserStatus", addUserStatus);
        model.addAttribute("isValid", addUserStatus.isValid());
        return "addUser";
    }

    @RequestMapping(value = "/forms")
    public String forms() {
        return "forms";
    }


    @RequestMapping(value = "/logIn")
    public String logIn(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password,
            Model model) {
        boolean authorized = userService.isAuthorized(login, password);
        model.addAttribute("authorized", authorized);
        return "logIn";
    }
}
