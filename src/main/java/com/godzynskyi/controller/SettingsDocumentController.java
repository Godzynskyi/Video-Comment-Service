package com.godzynskyi.controller;

import com.godzynskyi.domain.*;
import com.godzynskyi.service.DocumentService;
import com.godzynskyi.service.UserDocumentCredentialsService;
import com.godzynskyi.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Java Developer on 11.10.2015.
 */
@Controller
public class SettingsDocumentController {

    private static final Logger logger = Logger.getLogger(SettingsDocumentController.class);

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserDocumentCredentialsService userDocumentCredentialsService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/set_doc/{docId}")
    public ModelAndView setDocument(
            @PathVariable("docId") long docId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(username);
        Document document = documentService.getDocument(docId);

        boolean hasCredentials = hasCredentials(user, document);
        if(!hasCredentials) return new ModelAndView("redirect:/403");


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("document",document);

        List<UserDocumentCredential> credentials = userDocumentCredentialsService.getCredentials(document);
        modelAndView.addObject("credentials", credentials);

        modelAndView.setViewName("document_settings");
        return modelAndView;
    }

    @RequestMapping(value = "/set_doc/add_cred", method = RequestMethod.POST)
    public String addCred(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "credential") Credentials credentials,
            @RequestParam(name = "docId") long docId ) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(userName);

        Document document = documentService.getDocument(docId);
        boolean hasCredentials = hasCredentials(user, document);
        if(!hasCredentials) return "redirect:/403";

        user = userService.getUser(username);
        if(user == null) return "redirect:/set_doc/" + docId;
        if(userDocumentCredentialsService.getCredentials(user.getId(), docId) != null) return "redirect:/set_doc/" + docId;

        UserDocumentCredential userDocumentCredential = new UserDocumentCredential(user, document, credentials);
        boolean isAdded = userDocumentCredentialsService.addCredentials(userDocumentCredential);
        if(isAdded) logger.debug("Credentials successfully added.");
                else logger.error("Something wrong with adding this document.");

        return "redirect:/set_doc/" + docId;

    }

    @RequestMapping(value = "/set_doc/edit_cred", method = RequestMethod.POST)
    public String editCred(
            @RequestParam(name = "userId") long userId,
            @RequestParam(name = "credential") Credentials credentials,
            @RequestParam(name = "docId") long docId ) {


        Document document = documentService.getDocument(docId);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(userName);

        boolean hasCredentials = hasCredentials(user, document);
        if(!hasCredentials) return "redirect:/403";

        user = userService.getUser(userId);
        UserDocumentCredential userDocumentCredential = userDocumentCredentialsService.getUDCredentials(user, document);
        userDocumentCredential.setCredentials(credentials);

        boolean isEdited = userDocumentCredentialsService.updateCredentials(userDocumentCredential);
        if(isEdited) logger.debug("Credentials successfully edited.");
                else logger.error("Something wrong with editing this document.");

        return "redirect:/set_doc/" + docId;
    }

    @RequestMapping(value = "/set_doc/del_cred", method = RequestMethod.POST)
    public String delCred(
            @RequestParam(name = "userId") long userId,
            @RequestParam(name = "docId") long docId ) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(userName);

        Document document = documentService.getDocument(docId);

        boolean hasCredentials = hasCredentials(user, document);
        if(!hasCredentials) return "redirect:/403";

        user = userService.getUser(userId);
        UserDocumentCredential userDocumentCredential = userDocumentCredentialsService.getUDCredentials(user, document);

        boolean isDeleted = userDocumentCredentialsService.delete(userDocumentCredential);
        if(isDeleted) logger.debug("Credentials successfully Deleted.");
        else logger.error("Something wrong with deleting this credentials.");

        return "redirect:/set_doc/" + docId;
    }

    @RequestMapping(value = "/set_doc/change_doc", method = RequestMethod.POST)
    public String changeDoc(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "status") Status status,
            @RequestParam(name="docId") long docId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(username);
        Document document = documentService.getDocument(docId);

        boolean hasCredentials = hasCredentials(user, document);
        if(!hasCredentials) return "redirect:/403";

        document.setTitle(title);
        document.setDescription(description);
        document.setStatus(status);

        boolean isChanged = documentService.update(document);
        if(isChanged) logger.debug("Document successfully changed.");
                else logger.error("Something wrong with changing this document.");


        return "redirect:/set_doc/" + docId;
    }

    @RequestMapping(value = "/set_doc/deldoc")
    private String deleteDocument(
            @RequestParam(name = "id") long docId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(username);

        Document document = documentService.getDocument(docId);

        boolean hasCredentials = hasCredentials(user, document);
        if(!hasCredentials) return "redirect:/403";

        boolean isDeleted = documentService.deleteDocument(document);
        if(isDeleted) logger.debug("Document successfully deleted.");
                else logger.error("Something wrong with deleting this document.");

        return "redirect:/";
    }

    private boolean hasCredentials(User user, Document document) {
        if(user == null || document ==null) return false;
        if(document.getOwner().getId() == user.getId()) return true;
        if(user.getRoles().contains(UserRole.ADMIN_ROLE)) return true;
        System.out.println("userId: " + user.getId());
        System.out.println("docId:" + document.getId());
        System.out.println(userDocumentCredentialsService.getCredentials(user.getId(), document.getId()));
        if(Credentials.ADMIN == userDocumentCredentialsService.getCredentials(user.getId(), document.getId())) return true;
        return false;
    }
}
