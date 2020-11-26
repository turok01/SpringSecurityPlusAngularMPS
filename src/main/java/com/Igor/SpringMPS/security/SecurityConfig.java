package com.Igor.SpringMPS.security;

import com.Igor.SpringMPS.data.UserRepository;
import com.Igor.SpringMPS.services.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import java.security.AuthProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    //@Autowired
    //private UserRepository userRepository;

    //@Bean
    //public PasswordEncoder encoder(){
     //   //return new StandardPasswordEncoder("53cr3t");
      //  return new BCryptPasswordEncoder();
       // //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ////return passwordEncoder;
    //}

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(customAuthenticationProvider);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //you have to disable csrf Protection because it is enabled by default in spring
        http.cors().and().csrf().disable();
        //
        http.authorizeRequests()
                //.antMatchers("/select", "/addnew","/edit/current")
                     .antMatchers("/addnew","/edit/current")
                     //  .access("hasRole('ROLE_USER')" )
                .access("hasAnyAuthority('USER')" )

                //.antMatchers("/rest/**","/","/**")
                .antMatchers("/rest/**","/login","/registrationRush")
                    .access("permitAll")
                .anyRequest().authenticated()
        .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/select", true)
        .and()
                .logout().logoutSuccessUrl("/login");

    }
}
