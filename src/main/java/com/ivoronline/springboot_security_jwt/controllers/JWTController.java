package com.ivoronline.springboot_security_jwt.controllers;

import com.ivoronline.springboot_security_jwt.config.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JWTController {

  @Autowired JWTUtil jwtUtil;

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