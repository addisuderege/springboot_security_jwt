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

    //EXTRACT JWT FROM AUTHORIZATION HEADER
    String jwt = JWTUtil.extractJWTFromAuthorizationHeader(authorization);

    //GET CLAIMS
    Claims claims = JWTUtil.decodeJWT(jwt);

    //RETURN CLAIMS
    return claims;

  }

}