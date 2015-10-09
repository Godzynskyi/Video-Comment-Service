package com.godzynskyi.controller;

import com.godzynskyi.domain.Document;
import com.godzynskyi.domain.User;
import com.godzynskyi.domain.UserDocumentCredential;
import com.godzynskyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SpringSessionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Java Developer on 09.10.2015.
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.getUser(username);
        List<Document> myDocuments = user.getUserDocuments();
        modelAndView.addObject(myDocuments);
        for(Document document:myDocuments) {
            System.out.println("id: " + document.getId());
            System.out.println("Title: " + document.getTitle());
            System.out.println();
        }

        List<UserDocumentCredential> myDocumentCredentials = user.getCredentials();
        modelAndView.addObject(myDocumentCredentials);
        for(UserDocumentCredential cred: myDocumentCredentials) {
            System.out.println("Title:" + cred.getDocument());
            System.out.println("Cred:" + cred.getCredentials());
            System.out.println();
        }

        modelAndView.setViewName("main");
        return modelAndView;
    }
}
