package com.godzynskyi.controller;

import com.godzynskyi.domain.Document;
import com.godzynskyi.domain.User;
import com.godzynskyi.domain.UserDocumentCredential;
import com.godzynskyi.service.DocumentService;
import com.godzynskyi.service.UserDocumentCredentialsService;
import com.godzynskyi.service.UserService;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserDocumentCredentialsService userDocumentCredentialsService;

    @RequestMapping(value = "/")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(username);

//        List<Document> myDocuments = user.getUserDocuments();
        List<Document> myDocuments = documentService.findDocuments(user.getId());
        modelAndView.addObject("myDocuments", myDocuments);

//        List<UserDocumentCredential> myDocumentCredentials = user.getCredentials();
        List<UserDocumentCredential> myDocumentCredentials = userDocumentCredentialsService.getCredentials(user);
        modelAndView.addObject("myDocumentCredentials", myDocumentCredentials);

        modelAndView.setViewName("main");
        return modelAndView;
    }
}
