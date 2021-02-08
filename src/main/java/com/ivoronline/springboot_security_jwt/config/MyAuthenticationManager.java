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

      //HARD CODED USER
      //Here you would call UserDetailsService(enteredUsername) to get UserDetails with Password & Authorities.
      String username = "myuser";
      String password = "mypassword";
      String role     = "ROLE_USER";

      //GET ENTERED CREDENTIALS
      String enteredUsername = (String) enteredAuthentication.getPrincipal();   //USERNAME
      String enteredPassword = (String) enteredAuthentication.getCredentials(); //PASSWORD

      //AUTHENTICATE USER
      if (!enteredUsername.equals(username)) { System.out.println("Username not found"); return null; }
      if (!enteredPassword.equals(password)) { System.out.println("Incorrect Password"); return null; }

      //CREATE AUTHORITIES
      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                             authorities.add(new SimpleGrantedAuthority(role));

      //CREATE VALIDATED AUTHENTICATION
      Authentication validatedAuth = new UsernamePasswordAuthenticationToken(username,password,authorities);

      //RETURN VALIDATES AUTHENTICATION
      return validatedAuth;

    }

}
