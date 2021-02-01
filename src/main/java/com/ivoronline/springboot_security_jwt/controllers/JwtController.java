package com.ivoronline.springboot_security_jwt.controllers;

import com.ivoronline.springboot_security_jwt.config.JwtResponse;
import com.ivoronline.springboot_security_jwt.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private JwtTokenUtil jwtTokenUtil;
  @Autowired private UserDetailsService userDetailsService;

  @RequestMapping("/authenticate")
  public ResponseEntity<?> createAuthenticationToken() throws Exception {

    System.out.println("authenticate");

    String username = "myuser";
    String password = "mypassword";

    //authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    authenticate(username, password);

    final UserDetails userDetails = userDetailsService .loadUserByUsername(username);
    final String token = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new JwtResponse(token));

  }

  private void authenticate(String username, String password) throws Exception {

    try { authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); }
    catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }

  }

}