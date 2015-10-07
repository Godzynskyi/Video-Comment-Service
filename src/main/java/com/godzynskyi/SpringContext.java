package com.godzynskyi;

import com.godzynskyi.dao.*;
import com.godzynskyi.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Java Developer on 30.09.2015.
 */
@Configuration
@ComponentScan("com.godzynskyi")
//@EnableWebMvc
public class SpringContext {
//    @Bean
//    public UrlBasedViewResolver setupViewResolver() {
//        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//        resolver.setPrefix("/WEB-INF/pages/");
//        resolver.setSuffix(".jsp");
//        resolver.setViewClass(JstlView.class);
//        resolver.setOrder(1);
//        return resolver;
//    }
//
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        return new CommonsMultipartResolver();
//    }

    @Bean(name = "em")
    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServiceForVideoCommentsJPA");
        return emf.createEntityManager();
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAOimpl();
    }

    @Bean
    public UserService userService() {return new UserServiceImpl(); }

    @Bean
    public DocumentDAO documentDAO() {
        return new DocumentDAOImpl();
    }

    @Bean
    public DocumentService documentService() {
        return new DocumentServiceImpl();
    }

    @Bean
    public CommentDAO commentDAO() {
        return new CommentDAOImpl();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceImpl();
    }

    @Bean
    public VideoDAO videoDAO() {
        return new VideoDAOImpl();
    }

    @Bean
    public VideoService videoService() {
        return new VideoSevriceImpl();
    }

}

