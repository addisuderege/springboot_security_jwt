package com.ivoronline.springboot_security_jwt.controllers;

import com.ivoronline.springboot_security_jwt.config.JWTUtil;
import org.springframework.stereotype.Controller;
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

}


