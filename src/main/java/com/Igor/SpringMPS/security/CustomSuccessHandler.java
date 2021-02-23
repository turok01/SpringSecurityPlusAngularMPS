package com.Igor.SpringMPS.security;


import com.Igor.SpringMPS.data.TransformerRepository;
import com.Igor.SpringMPS.data.UserRepository;
import com.Igor.SpringMPS.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username, useremail;
        //if redirected from some specific url, need to remove the cachedRequest to force use defaultTargetUrl
        final RequestCache requestCache = new HttpSessionRequestCache();
        final SavedRequest savedRequest = requestCache.getRequest(request, response);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User principal = ((OAuth2User) authentication.getPrincipal());
            username = principal.getName();
            Map <String, Object> attributesMap = principal.getAttributes();
            username = attributesMap.get("name").toString();
            useremail = attributesMap.get("email").toString();

        }
        if (authentication.getPrincipal() instanceof OidcUser) {
            OidcUser principal = ((OidcUser) authentication.getPrincipal());
            username = principal.getName();
            useremail = principal.getEmail();

        }

        /*if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            User user = (User)auth.getPrincipal();

            if (user != null) {
                username = user.getUsername();
                useremail = user.toString();
                userRepository.save(user);

            } else {
                username = "ANONYMOUS";
            }
        }*/


        //username = authentication.getPrincipal().getClass();
        //useremail = authentication.getPrincipal().getAttribute("email");

        //You can let Spring security handle it for you.
        super.onAuthenticationSuccess(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
}
