package com.ivoronline.springboot_security_jwt.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class JWTUtil {

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
    String secretKey         = "mysecretkey";
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
    Key    signingKey        = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    //----------------------------------------------------------------------
    // GENERATE JWT
    //----------------------------------------------------------------------
    String jwt = builder.signWith(signatureAlgorithm, signingKey).compact();
    return jwt;

  }

}
