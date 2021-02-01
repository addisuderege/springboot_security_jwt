package com.ivoronline.springboot_security_jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class JWTUtil {

  //USED TO BOTH CREATE & DECODE JWT
  public final static String SECRET_KEY = "mysecretkey";

  //========================================================================
  // CREATE JWT
  //========================================================================
  public static String createJWT(String id, String issuer, String subject) {

    //----------------------------------------------------------------------
    // HEADER (SPECIFY ALGORITHM)
    //----------------------------------------------------------------------
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    //----------------------------------------------------------------------
    // PAYLOAD (SPECIFY CLAIMS)
    //----------------------------------------------------------------------
    JwtBuilder builder = Jwts.builder()
      .setId     (id)
      .setIssuer (issuer)
      .setSubject(subject);

    //----------------------------------------------------------------------
    // SIGNATURE (SPECIFY SECRET KEY)
    //----------------------------------------------------------------------
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    Key    signingKey        = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    //----------------------------------------------------------------------
    // GENERATE JWT
    //----------------------------------------------------------------------
    String jwt = builder.signWith(signatureAlgorithm, signingKey).compact();
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
