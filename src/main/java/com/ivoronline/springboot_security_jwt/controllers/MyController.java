package com.ivoronline.springboot_security_jwt.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

  @ResponseBody
  @PreAuthorize("hasAuthority('book.create')")
  @RequestMapping("/CreateBook")
  public String createBook() {
    return "Only ADMIN can create Book";
  }

}
