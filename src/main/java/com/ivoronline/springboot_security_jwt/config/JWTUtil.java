package com.ivoronline.springboot_security_jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

  //USED TO CREATE & DECODE JWT
  public final static String SECRET_KEY = "mysecretkey";

  //========================================================================
  // CREATE JWT
  //========================================================================
  public String createJWT(String username, String authorities) {

    //HEADER (SPECIFY ALGORITHM)
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    //PAYLOAD (SPECIFY CLAIMS)
    Map<String, Object> customClaims = new HashMap<>();
                        customClaims.put("username"   , username);
                        customClaims.put("authorities", authorities);

    JwtBuilder builder = Jwts.builder()
      .setClaims (customClaims)  //Place them first not to override subsequent Claims
      .setId     ("1")
      .setSubject("TestJWT")
      .setIssuer ("ivoronline");

    //SIGNATURE (SPECIFY SECRET KEY)
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    Key    signingKey        = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    //GENERATE JWT
    String jwt = builder.signWith(signatureAlgorithm, signingKey).compact();
    return jwt;

  }

  //========================================================================
  // DECODE JWT
  //========================================================================
  public Claims decodeJWT(String jwt) {

    //GET CLAIMS
    Claims claims = Jwts.parser()
      .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
      .parseClaimsJws(jwt).getBody();

    //RETURN CLAIMS
    return claims;

  }

}
