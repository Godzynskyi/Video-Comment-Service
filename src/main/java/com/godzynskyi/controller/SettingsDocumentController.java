package com.godzynskyi.controller;

import com.godzynskyi.domain.Credentials;
import com.godzynskyi.domain.Document;
import com.godzynskyi.domain.User;
import com.godzynskyi.domain.UserDocumentCredential;
import com.godzynskyi.service.DocumentService;
import com.godzynskyi.service.UserDocumentCredentialsService;
import com.godzynskyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Java Developer on 11.10.2015.
 */
@Controller
public class SettingsDocumentController {
    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserDocumentCredentialsService userDocumentCredentialsService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/set_doc/{id}")
    public ModelAndView setDocument(
            @PathVariable("id") long docId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(username);
        Document document = documentService.getDocument(docId);

        boolean hasCredentials = false;
        if(document.getOwner().getId() == user.getId()) hasCredentials = true;
        if(userDocumentCredentialsService.getCredentials(user.getId(),docId) == Credentials.ADMIN) hasCredentials = true;
        if(!hasCredentials) return new ModelAndView("redirect:/403");


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("document",document);

        List<UserDocumentCredential> credentials = userDocumentCredentialsService.getCredentials(document);
        modelAndView.addObject("credentials", credentials);

        modelAndView.setViewName("document_settings");
        return modelAndView;

    }

}
