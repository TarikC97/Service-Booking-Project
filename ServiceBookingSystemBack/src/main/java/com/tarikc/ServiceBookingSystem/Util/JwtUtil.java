package com.tarikc.ServiceBookingSystem.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    public  static final String SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQSflKxwRJSMeKKF2QT4fwpMeJf36POk6yJVadQssw5c";

    private String createToken(Map<String,Object> claims,String userName){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(),SignatureAlgorithm.HS256)
                .compact();

    }
    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(String userName){
        Map<String,Object> claims = new HashMap<>();
        return  createToken(claims,userName);
    }
    private Jws<Claims> extractAllClaims(String token){
        return  Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final  Claims claims = (Claims) extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }
    public Date extractExpiration(String token){
        return  extractClaim(token,Claims::getExpiration);
    }
    public String extractUsername(String token){
        return  extractClaim(token,Claims::getSubject);
    }
    public Boolean isTokenExpired(String token){
        return  extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final  String username = extractUsername(token);
        return  (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
