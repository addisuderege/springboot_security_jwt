package com.ivoronline.springboot_security_jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private MyFilter myFilter;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    //ANONYMOUS ACCESS
    httpSecurity.authorizeRequests().antMatchers("/CreateJWT"  ).permitAll();                //To get JWT
    httpSecurity.authorizeRequests().antMatchers("/GetClaims"  ).permitAll();                //For Testing
    httpSecurity.authorizeRequests().antMatchers("/GetUsername").permitAll();                //For Testing

    //OTHER CONFIGURATION
    httpSecurity.csrf().disable();                                                           //Enables POST
    httpSecurity.authorizeRequests().anyRequest().authenticated();                           //Authenticated
    httpSecurity.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);      //Add Filter
    httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //No Session

  }

}
