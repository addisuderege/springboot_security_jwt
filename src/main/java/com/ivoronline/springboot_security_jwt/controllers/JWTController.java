package com.ivoronline.springboot_security_jwt.controllers;

import com.ivoronline.springboot_security_jwt.config.JWTUtil;
import com.ivoronline.springboot_security_jwt.config.MyAuthenticationManager;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JWTController {

  @Autowired JWTUtil                 jwtUtil;
  @Autowired MyAuthenticationManager myAuthenticationManager;

  //==================================================================
  // GET JWT
  //==================================================================
  @ResponseBody
  @RequestMapping("/GetJWT")
  public String getJWT(@RequestParam String enteredUsername, @RequestParam String enteredPassword) {

    //AUTHENTICATE (COMPARE ENTERED AND STORED CREDENTIALS)
    Authentication enteredAuth  = new UsernamePasswordAuthenticationToken(enteredUsername, enteredPassword);
    Authentication returnedAuth = myAuthenticationManager.authenticate(enteredAuth);

    //CHECK AUTHENTICATION
    if(returnedAuth == null) { return "User is NOT Authenticated"; }

    //CREATE JWT
    String username = (String) returnedAuth.getPrincipal();
    String role     = (String) returnedAuth.getAuthorities().toString().replace("[","").replace("]","");
    String jwt      = jwtUtil.createJWT(username, role);

    //RETURN JWT
    return jwt;

  }

  //=============================================================
  // CREATE JWT
  //=============================================================
  @ResponseBody
  @RequestMapping("/CreateJWT")
  public String createJWT() {

    //CREATE JWT
    String jwt = jwtUtil.createJWT("myuser", "ROLE_USER");

    //RETURN JWT
    return jwt;

  }

  //=============================================================
  // GET CLAIMS
  //=============================================================
  @ResponseBody
  @RequestMapping("/GetClaims")
  public Claims getClaims(@RequestHeader("Authorization") String authorization) {

    //EXTRACT JWT FROM AUTHORIZATION HEADER
    String jwt = jwtUtil.extractJWTFromAuthorizationHeader(authorization);

    //GET CLAIMS
    Claims claims = jwtUtil.getClaims(jwt);

    //RETURN CLAIMS
    return claims;

  }

  //=============================================================
  // GET USERNAME
  //=============================================================
  @ResponseBody
  @RequestMapping("/GetUsername")
  public String getUsername(@RequestHeader("Authorization") String authorization) {

    //EXTRACT JWT FROM AUTHORIZATION HEADER
    String jwt = jwtUtil.extractJWTFromAuthorizationHeader(authorization);

    //GET USERNAME
    String username = jwtUtil.getUsername(jwt);

    //RETURN USERNAME
    return username;

  }

}