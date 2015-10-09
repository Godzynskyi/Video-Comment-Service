package com.godzynskyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@SpringBootApplication
@Import({SpringContext.class, SecurityConfig.class})
@EnableWebMvcSecurity
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
