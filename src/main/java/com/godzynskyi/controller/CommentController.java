package com.godzynskyi.controller;

import com.godzynskyi.domain.*;
import com.godzynskyi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Java Developer on 13.10.2015.
 */
@Controller
public class CommentController {

    @Autowired
    private UserDocumentCredentialsService userDocumentCredentialsService;
    @Autowired
    private UserService userService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/doc")
    public ModelAndView showDocument(
            @RequestParam(name = "id") long docId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(username);
        Document document = documentService.getDocument(docId);

        String credentials = credentials(user, document);
        if(credentials == null) return new ModelAndView("redirect:/403");

        List<Comment> comments = commentService.getComments(docId);

        ModelAndView modelAndView = new ModelAndView("/doc");
        modelAndView.addObject("credentials", credentials);
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("docId", docId);
        return modelAndView;
    }

    @RequestMapping(value = "/add_comment", method = RequestMethod.POST)
    public String addComment(
            @RequestParam(name = "docId") long docId,
            @RequestParam(name = "comment") String comment,
            @RequestParam(name = "start_time") int startTime,
            @RequestParam(name = "end_time", required = false, defaultValue = "-1") int endTime,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "style") String style,
            @RequestParam(name = "url") String url,
            @RequestParam(name = "position") int index) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(username);
        Document document = documentService.getDocument(docId);

        String credentials = credentials(user, document);
        if(credentials == null || credentials.equals("READ")) return "redirect:/403";

        Video video = videoService.getVideo(url);

        Comment com = new Comment(document, video, comment);
        com.setDescription(description);
        com.setStyle(style);
        com.setStartTime(startTime);
        com.setEndTime(endTime);
        com.setIndex(index);
        commentService.createComment(com, index);

        return "redirect:/doc?id=" + docId;
    }

    private String credentials(User user, Document document) {
        if(document==null) return null;
            if(user == null) {
                if(document.getStatus() == Status.PRIVATE) return null; else return "READ";
            }
        if(user.getRoles().contains(UserRole.ADMIN_ROLE)) return "ADMIN";
        if(document.getOwner().getId() == user.getId()) return "ADMIN";
        Credentials credentials = userDocumentCredentialsService.getCredentials(user.getId(), document.getId());
        if(credentials == null) {
            if(document.getStatus() == Status.PRIVATE) return null; else return "READ";
        }
        switch (credentials) {
            case ADMIN:
                return "ADMIN";
            case WRITE:
                return "WRITE";
            case READ:
                return "READ";
            default:
                return null;
        }
    }
}
