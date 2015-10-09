package com.godzynskyi;

import com.godzynskyi.dao.*;
import com.godzynskyi.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Java Developer on 30.09.2015.
 */
@Configuration
@ComponentScan("com.godzynskyi")
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

}

