package com.hnxx.zy.clms.security.jwt;

import com.hnxx.zy.clms.security.test.entity.SysUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: nile
 * @create: 2020-03-22 22:19
 * Jwt参数配置类
 */

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private String jwtSecret = "thisistest";

    private int jwtExpiration = 1000000;

    public String generateJwtToken(Authentication authentication) {

        SysUser userPrincipal = new SysUser();
        userPrincipal.setUsername(authentication.getName());

        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: { } ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: { }", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: { }", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: { }", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: { }", e);
        }
        
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
}