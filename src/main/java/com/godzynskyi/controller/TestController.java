package com.godzynskyi.controller;

import com.godzynskyi.dao.DocumentDAO;
import com.godzynskyi.dao.VideoDAO;
import com.godzynskyi.domain.*;
import com.godzynskyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Java Developer on 06.10.2015.
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private DocumentDAO documentDAO;

    @Autowired
    private VideoDAO videoDAO;

//    @RequestMapping(value = "/")
//    public String test(
//            @RequestParam(value = "name", required = false, defaultValue = "ivan") String name,
//            Model model ) {
//
//        User user = userService.getUser(name);
//
//        model.addAttribute("name", user.getPassword());
//        return "test";
//
//    }

    @RequestMapping(value = "/test")
    public String test(Model model) {
        User user = userService.getUser("anna");
        Document document = documentDAO.getDocument(1);
        List<Comment> comments = document.getComments();
        for(Comment c : comments) {
            System.out.println(c.getComment());
        }

        return "test";
    }

}
