package com.ivoronline.springboot_security_jwt.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MyFilter implements Filter {

  @Autowired JWTUtil jwtUtil;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
    throws IOException, ServletException {

    //CAST TO GET ACCESS TO HEADERS
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    //GET AUTHORIZATION HEADER
    String authorizationHeader = httpRequest.getHeader("Authorization");

    if(authorizationHeader != null) {

      //EXTRACT JWT FROM AUTHORIZATION HEADER
      String jwt = jwtUtil.extractJWTFromAuthorizationHeader(authorizationHeader);

      //GET CLAIMS
      Claims claims   = jwtUtil.getClaims(jwt);
      String username = (String) claims.get("role");
      String role     = (String) claims.get("username");

      //CREATE AUTHORITIES
      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
      authorities.add(new SimpleGrantedAuthority(role));

      //AUTHENTICATE
      Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

      //STORE AUTHENTICATION INTO CONTEXT (SESSION)
      SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    //FORWARD REQUEST
    filterchain.doFilter(request, response);

  }

}
