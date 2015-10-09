package com.godzynskyi.controller;

import com.godzynskyi.domain.User;
import com.godzynskyi.service.UserService;
import com.godzynskyi.validation.AddUserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Java Developer on 09.10.2015.
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("msg",new String(""));
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "password1") String password1,
            Model model) {
        String msg = "";
        AddUserStatus addUserStatus = new AddUserStatus();
        if(userService.getUser(username) != null) {
            addUserStatus.setNotUserExist(false);
            msg = "Login '" + username + "' is exist. ";
        }
        if(!password.equals(password1)) {
            addUserStatus.setPasswordsEquals(false);
            msg += "You must confirm password. ";
        }
        if(!addUserStatus.getIsValid()) {
            model.addAttribute("msg", msg);
            return "register";
        }

        addUserStatus = userService.addNewUser(new User(username, password));
        if(!addUserStatus.getIsAdded()) {
            msg = "Some problems with DB, try later. ";
            model.addAttribute("msg", msg);
            return "register";
        }

        msg = "Congratulations!!! You were successfully registered. Enter your new account: ";
        model.addAttribute("msg", msg);
        return "login";
    }
}
