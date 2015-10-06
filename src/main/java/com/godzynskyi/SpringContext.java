package com.godzynskyi;

import com.godzynskyi.dao.IUserDAO;
import com.godzynskyi.dao.UserDAO;
import com.godzynskyi.service.IUserService;
import com.godzynskyi.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

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
    public IUserDAO userDao() {
        return new UserDAO();
    }

    @Bean
    public IUserService userService() {return new UserService(); }
}

