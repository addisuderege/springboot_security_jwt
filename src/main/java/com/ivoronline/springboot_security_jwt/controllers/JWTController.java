package com.ivoronline.springboot_security_jwt.controllers;

import com.ivoronline.springboot_security_jwt.config.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    String jwt = jwtUtil.createJWT("admin", "[book.create, book.delete]");

    //RETURN JWT
    return jwt;

  }

  //=============================================================
  // DECODE JWT
  //=============================================================
  @ResponseBody
  @RequestMapping("/DecodeJWT")
  public Claims decodeJWT(@RequestParam String jwt) {

    //GET CLAIMS
    Claims claims = jwtUtil.decodeJWT(jwt);

    //RETURN CLAIMS
    return claims;

  }

}