package com.godzynskyi.controller;

import com.godzynskyi.domain.Document;
import com.godzynskyi.domain.Status;
import com.godzynskyi.domain.User;
import com.godzynskyi.service.DocumentService;
import com.godzynskyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Java Developer on 10.10.2015.
 */
@Controller
public class CreateDocumentController {

    @Autowired
    private UserService userService;

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/create_document")
    public String createDocument() {
        return "create_document";
    }

    @RequestMapping(value = "/addDoc", method = RequestMethod.POST)
    public String addDocument(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "status") Status status) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(username);

        Document document = new Document(title, user, status);
        document.setDescription(description);
        boolean isAdded = documentService.createDocument(document);


        if(isAdded) return "redirect:/"; else return "redirect:/403";
    }
}
