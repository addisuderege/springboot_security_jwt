package com.ivoronline.springboot_security_jwt.controllers;

import com.ivoronline.springboot_security_jwt.config.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JWTController {

  @ResponseBody
  @RequestMapping("/CreateJWT")
  public String createJWT() {
    String jwt = JWTUtil.createJWT("mysuser", "ROLE_USER");
    return jwt;
  }

  @ResponseBody
  @RequestMapping("/DecodeJWT")
  public Claims decodeJWT(@RequestHeader("Authorization") String authorization) {

    //GET AUTHORIZATION HEADER
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      System.out.println("Authorization Header not found");
      return null;
    }

    //GET JWT
    String jwt = authorization.substring(7);

    //GET CLAIMS
    Claims claims = JWTUtil.decodeJWT(jwt);

    //RETURN CLAIMS
    return claims;

  }

}