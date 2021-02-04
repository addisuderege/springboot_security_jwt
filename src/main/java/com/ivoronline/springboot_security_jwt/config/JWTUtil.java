package com.ivoronline.springboot_security_jwt.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

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
    String secretKey         = "mysecretkey";
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
    Key    signingKey        = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    //GENERATE JWT
    String jwt = builder.signWith(signatureAlgorithm, signingKey).compact();
    return jwt;

  }

}





