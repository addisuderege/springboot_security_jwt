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
      String storedUsername = "myuser";
      String storedPassword = "mypassword";
      String storedRole     = "ROLE_USER";

      //AUTHENTICATE USER (COMPARE ENTERED AND STORED CREDENTIALS)
      if (!enteredUsername.equals(storedUsername)) { System.out.println("Username not found"); return null; }
      if (!enteredPassword.equals(storedPassword)) { System.out.println("Incorrect Password"); return null; }

      //CREATE AUTHORITIES
      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                             authorities.add(new SimpleGrantedAuthority(storedRole));

      //CREATE VALIDATED AUTHENTICATION
      Authentication validatedAuth = new UsernamePasswordAuthenticationToken(storedUsername,storedPassword,authorities);

      //RETURN VALIDATES AUTHENTICATION
      return validatedAuth;

    }

}
