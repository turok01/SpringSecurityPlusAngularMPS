package com.Igor.SpringMPS.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomOidcUserService customOidcUserService;

    @Bean
    public PasswordEncoder encoder(){
        return new StandardPasswordEncoder("53cr3t");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //you have to disable csrf Protection because it is enabled by default in spring
        //http.cors().and().csrf().disable();
        //
        http.authorizeRequests()
                //.antMatchers("/select", "/addnew","/edit/current")
                    //   .access("hasRole('ROLE_USER')")
                .antMatchers("/oauth_login","/static/**","/rest/**")//,"/","/**")
                .permitAll()//.access("permitAll")
                .anyRequest().authenticated()
        .and()
                .oauth2Login()
                .loginPage("/oauth_login")
                .successHandler(successHandler())
        .and()
                //register user-defined OuidcUserService
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(customOidcUserService);
                //.formLogin().loginPage("/login")
                //.defaultSuccessUrl("/select", true)
        /*.and()
                .logout().logoutSuccessUrl("/login");*/

    }
    @Bean
    public CustomSuccessHandler successHandler() {
        return new CustomSuccessHandler();
    }
}
