package com.ivoronline.springboot_security_jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

  //USED TO CREATE & DECODE JWT
  public final static String SECRET_KEY = "mysecretkey";

  //========================================================================
  // CREATE JWT
  //========================================================================
  public static String createJWT(String username, String role) {

    //HEADER (SPECIFY ALGORITHM)
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    //PAYLOAD (SPECIFY CLAIMS)
    Map<String, Object> customClaims = new HashMap<>();
                        customClaims.put("username", username);
                        customClaims.put("role"    , role);

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
  // EXTRACT JWT FROM AUTHORIZATION HEADER
  //========================================================================
  public static String extractJWTFromAuthorizationHeader(String authorization) {

    //GET AUTHORIZATION HEADER
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      System.out.println("Authorization Header not found");
      return null;
    }

    //GET JWT
    String jwt = authorization.substring(7);

    //RETURN JWT
    return jwt;

  }

  //========================================================================
  // DECODE JWT
  //========================================================================
  public static Claims decodeJWT(String jwt) {

    //GET CLAIMS
    Claims claims = Jwts.parser()
      .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
      .parseClaimsJws(jwt).getBody();

    //RETURN CLAIMS
    return claims;

  }

}
