package io.umbrella.Springfeatures.controllers;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class LoginController {


    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public LoginController(OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @RequestMapping("/login/github")
    public String getOidcUserPrincipal(OAuth2AuthenticationToken principal) {
        System.out.println(principal);
        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(principal.getAuthorizedClientRegistrationId(), principal.getPrincipal().getName());

        String getEmailUrl = "https://api.github.com/user/emails";

        // send HTTP request to get user emails
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
        HttpEntity entity = new HttpEntity("", headers);
        ResponseEntity response = restTemplate.exchange(getEmailUrl, HttpMethod.GET, entity, String.class);
        return response.getBody().toString();
    }

}
