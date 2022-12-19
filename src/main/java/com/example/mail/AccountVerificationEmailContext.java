package com.example.mail;

import com.example.Entity.User;
import org.springframework.web.util.UriComponentsBuilder;

public class AccountVerificationEmailContext extends AbstractEmailContext {

    private String token;



    @Override
    public  void init(Object context){
        //we can do any common configuration setup here
        // like setting up some base URL and context
        User user = (User) context; // we pass the customer informati
        put("username", user.getUsername());
        setTemplateLocation("emails/email-verification");
        setSubject("Complete your registration");
        setFrom("botkinkirill2@gmail.com");
        setTo(user.getMail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }


    //for api path("/api/email/verify")
    //for site path("/verify")
    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}