package com.godzynskyi.controller;

import com.godzynskyi.domain.Credentials;
import com.godzynskyi.domain.Document;
import com.godzynskyi.domain.User;
import com.godzynskyi.service.DocumentService;
import com.godzynskyi.service.UserDocumentCredentialsService;
import com.godzynskyi.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Java Developer on 11.10.2015.
 */
@Controller
public class DeleteDocumentController {

    private static final Logger logger = Logger.getLogger(DeleteDocumentController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserDocumentCredentialsService userDocumentCredentialsService;

    @RequestMapping(value = "/deldoc")
    private String deleteDocument(
            @RequestParam(name = "id") long docId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(username);

        Document document = documentService.getDocument(docId);
        if(document == null) return "redirect:/";

        boolean hasCredentials = false;
        if(document.getOwner().getId() == user.getId()) hasCredentials = true;
        if(userDocumentCredentialsService.getCredentials(user.getId(),docId) == Credentials.ADMIN) hasCredentials = true;
        if(!hasCredentials) return "redirect:/403";


        boolean isDeleted = documentService.deleteDocument(document);
        if(isDeleted) logger.debug("Document successfully deleted.");
        else logger.error("Something wrong with deleting this document.");

        return "redirect:/";
    }
}
