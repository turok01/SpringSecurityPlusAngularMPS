package com.Igor.SpringMPS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
//import java.net.http.HttpHeaders;

//import org.springframework.web.reactive.function.client.ClientRequest;
//import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Oauth2LoginController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    private static String authorizationRequestBaseUri="oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @RequestMapping("/oauth_login")
    public String getLoginPage(Model model){
        Iterable <ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
        if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])){
            clientRegistrations = (Iterable<ClientRegistration>)clientRegistrationRepository;
            clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(),
                    authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        }
        model.addAttribute("urls",oauth2AuthenticationUrls);
        return "oauth_login";
    }

    @RequestMapping("/currentuserinfo")
    public String currentuserinfo(Model model, OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),authentication.getName());
        String s_name = authentication.getPrincipal().getAttribute("name");
        model.addAttribute("name", s_name);

        /*
        */
        Map userAttributes = Collections.emptyMap();
        String userInfoEndpointUri = authorizedClient.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUri();
        if(!StringUtils.isEmpty(userInfoEndpointUri)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer" + authorizedClient.getAccessToken().getTokenValue());

            HttpEntity<String> entity = new HttpEntity<String>("", headers);

            ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
            userAttributes = response.getBody();
            model.addAttribute("name", userAttributes.get("name"));
        }
        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
        /**/

        //model.addAttribute("userAttributes", userAttributes);
        //model.addAttribute("userName", authentication.getName()); //sub

        return "currentuserinfo";
    }
}
