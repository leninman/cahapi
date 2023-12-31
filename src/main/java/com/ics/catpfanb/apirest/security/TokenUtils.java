package com.ics.catpfanb.apirest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;

public class TokenUtils {
    private static final String ACCESS_TOKEN_SECRET="a437a56e1a0e4461970b168a12bc2677";
    private static final Long ACCESS_TOKEN_VALIDITY_SECONDS= 2_592_000L;


    public static String createToken(String username){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + ACCESS_TOKEN_VALIDITY_SECONDS);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET)
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
                Claims claims = Jwts.parser()
                        .setSigningKey(ACCESS_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

            String username = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }


    }


}




