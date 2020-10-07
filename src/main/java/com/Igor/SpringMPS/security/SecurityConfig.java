package com.Igor.SpringMPS.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder(){
        return new StandardPasswordEncoder("53cr3t");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //you have to disable csrf Protection because it is enabled by default in spring
        http.cors().and().csrf().disable();
        //
        http.authorizeRequests()
                //.antMatchers("/select", "/addnew","/edit/current")
                    // .antMatchers("/addnew","/edit/current")
                    //   .access("hasRole('ROLE_USER')")
                .antMatchers("/rest/**","/","/**")
                    .access("permitAll")
        .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/select", true)
        .and()
                .logout().logoutSuccessUrl("/login");
    }
}
