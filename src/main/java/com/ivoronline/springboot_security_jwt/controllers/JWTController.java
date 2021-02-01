package com.ivoronline.springboot_security_jwt.controllers;

import com.ivoronline.springboot_security_jwt.config.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JWTController {

  @ResponseBody
  @RequestMapping("/CreateJWT")
  public String createJWT() {
    String jwt = JWTUtil.createJWT("1", "ivoronline", "TestJWT");
    return jwt;
  }

  @ResponseBody
  @RequestMapping("/DecodeJWT")
  public Claims decodeJWT(@RequestParam String jwt) {
    Claims claims = JWTUtil.decodeJWT(jwt);
    return claims;
  }

}