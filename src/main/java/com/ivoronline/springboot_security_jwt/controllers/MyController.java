package com.ivoronline.springboot_security_jwt.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

  @ResponseBody
  @RequestMapping("/ReadBook")
  @PreAuthorize("hasAuthority('book.read')")
  public String hello() {
    return "ADMIN can read books";
  }

}
