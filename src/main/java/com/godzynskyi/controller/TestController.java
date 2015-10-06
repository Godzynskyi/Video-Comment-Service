package com.godzynskyi.controller;

import com.godzynskyi.domain.User;
import com.godzynskyi.service.IUserService;
import com.godzynskyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Java Developer on 06.10.2015.
 */
@Controller
public class TestController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/")
    public String test(
            @RequestParam(value = "name", required = false, defaultValue = "ivan") String name,
            Model model ) {

        User user = userService.getUser(name);

        model.addAttribute("name", user.getPassword());
        return "test";

    }

}
