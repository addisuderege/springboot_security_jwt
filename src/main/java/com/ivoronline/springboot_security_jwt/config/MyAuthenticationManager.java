package com.ivoronline.springboot_security_jwt.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication enteredAuthentication) {

      //GET ENTERED CREDENTIALS
      String enteredUsername = (String) enteredAuthentication.getPrincipal();   //ENTERED USERNAME
      String enteredPassword = (String) enteredAuthentication.getCredentials(); //ENTERED PASSWORD

      //GET STORED CREDENTIALS
      //Here they are hard coded (for simplicity reason)
      //Call UserDetailsService(enteredUsername) to get UserDetails with Password & Authorities from DB
      String storedUsername    = "admin";
      String storedPassword    = "adminpassword";
      String storedAuthorities = "book.create, book.delete";

      //AUTHENTICATE USER (COMPARE ENTERED AND STORED CREDENTIALS)
      if (!enteredUsername.equals(storedUsername)) { System.out.println("Username not found"); return null; }
      if (!enteredPassword.equals(storedPassword)) { System.out.println("Incorrect Password"); return null; }

      //CREATE AUTHORITIES
      String[] authoritiesArray = storedAuthorities.split(", ");
      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
      for(String authority : authoritiesArray) {
        authorities.add(new SimpleGrantedAuthority(authority));
      }

      //CREATE VALIDATED AUTHENTICATION
      Authentication validatedAuth = new UsernamePasswordAuthenticationToken(enteredUsername, null, authorities);

      //RETURN VALIDATES AUTHENTICATION
      return validatedAuth;

    }

}
