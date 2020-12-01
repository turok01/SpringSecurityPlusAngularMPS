package com.Igor.SpringMPS.security;

import com.Igor.SpringMPS.data.UserRepository;
import com.Igor.SpringMPS.services.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.AuthProvider;
import java.util.List;

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

    private static String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";

    @Autowired
    private Environment env;

    @Bean
    public ClientRegistration getRegistration(String client) {
        String clientId = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-id");

        if (clientId == null) {
            return null;
        }

        String clientSecret = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-secret");

        if (client.equals("google")) {
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).build();
        }
        if (client.equals("facebook")) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).build();
        }
        return null;
    }
    @Bean
    @Autowired
    public ClientRegistrationRepository clientRegistrationRepository(List<ClientRegistration> registrations) {
        return new InMemoryClientRegistrationRepository(registrations);
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
                .oauth2Login()
        .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/select", true)
        .and()
                .logout().logoutSuccessUrl("/login");

    }
}
