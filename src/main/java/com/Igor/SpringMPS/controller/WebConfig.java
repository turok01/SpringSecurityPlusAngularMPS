package com.Igor.SpringMPS.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/login");

        //configuration, witch view will be a Spring Security login page
        registry.addViewController("/login").setViewName("login");
        //registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        //registry.addViewController("/").setViewName("home");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")
                .allowedOrigins("http://localhost:4200","https://springangularrest.herokuapp.com")
                .allowedMethods("*")
                .allowedHeaders("*")
                //.maxAge(-1)
                //.allowCredentials(false);
                .allowCredentials(true);
    }
}
