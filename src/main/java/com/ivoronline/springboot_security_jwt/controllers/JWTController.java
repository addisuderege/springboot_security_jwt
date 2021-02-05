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

  @ResponseBody
  @RequestMapping("/CreateJWT")
  public String createJWT() {

    //CREATE JWT
    String jwt = jwtUtil.createJWT("mysuser", "ROLE_USER");

    //RETURN JWT
    return jwt;

  }

  @ResponseBody
  @RequestMapping("/DecodeJWT")
  public Claims decodeJWT(@RequestHeader("Authorization") String authorization) {

    //EXTRACT JWT FROM AUTHORIZATION HEADER
    String jwt = jwtUtil.extractJWTFromAuthorizationHeader(authorization);

    //GET CLAIMS
    Claims claims = jwtUtil.decodeJWT(jwt);

    //RETURN CLAIMS
    return claims;

  }

}