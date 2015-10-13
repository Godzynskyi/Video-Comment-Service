package com.godzynskyi.controller;

import com.godzynskyi.domain.User;
import com.godzynskyi.service.UserService;
import com.godzynskyi.validation.AddUserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Java Developer on 08.10.2015.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }

    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            model.addObject("username", auth.getName());
            model.setViewName("403");
        return model;

    }

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
