package com.Igor.SpringMPS.security;

import com.Igor.SpringMPS.security.oauth2.CustomOAuth2UserService;
import com.Igor.SpringMPS.services.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    //@Autowired
    //private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    //@Autowired
    //private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    //@Autowired
    //private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    //@Bean
    //public TokenAuthenticationFilter tokenAuthenticationFilter() {      return new TokenAuthenticationFilter();   }

    /*
      By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
      the authorization request. But, since our service is stateless, we can't save it in
      the session. We'll save the request in a Base64 encoded cookie instead.
    */
    //@Bean
    //public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {        return new HttpCookieOAuth2AuthorizationRequestRepository();    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/auth/**", "/oauth2/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

        // Add our custom Token based authentication filter
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    //*----------
    private static List<String> clients = Arrays.asList("google", "facebook");

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c))
                .filter(registration -> registration != null)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private static String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";

    @Autowired
    private Environment env;

    private ClientRegistration getRegistration(String client) {
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
    public OAuth2AuthorizedClientService authorizedClientService(
            ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository(
            OAuth2AuthorizedClientService authorizedClientService) {
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
    }

//----------------*/
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


    /*
        @Autowired
        private CustomAuthenticationProvider customAuthenticationProvider;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(customAuthenticationProvider);
        }
    */



/*
    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {

        return new InMemoryOAuth2AuthorizedClientService(
                clientRegistrationRepository());
    }

 */
    @EnableWebSecurity
    public static class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private CustomAuthenticationProvider customAuthenticationProvider;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(customAuthenticationProvider);
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //you have to disable csrf Protection because it is enabled by default in spring
            http.cors().and().csrf().disable();
            //
            http.authorizeRequests()
                    //.antMatchers("/select", "/addnew","/edit/current")
                    .antMatchers("/addnew", "/edit/current")
                      .access("hasRole('ROLE_USER')" )// this ROLE in case of OAuth2 (google)
                    //.access("hasAnyAuthority('USER')") // this ROLE in case of my own realisation of Authorization

                    //.antMatchers("/rest/**","/","/**")
                    .antMatchers("/rest/**", "/login", "/registrationRush")
                    .access("permitAll")
                    .anyRequest().authenticated()
                    .and()
                    .oauth2Login()
                    .clientRegistrationRepository(clientRegistrationRepository())
                    .authorizedClientService(authorizedClientService())
                    .and()
                    .formLogin().loginPage("/login")
                    .defaultSuccessUrl("/select", true)
                    .and()
                    .logout().logoutSuccessUrl("/login");

        }
    }
}
