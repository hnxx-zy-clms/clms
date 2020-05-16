package com.hnxx.zy.clms.security.jwt;

import com.hnxx.zy.clms.core.entity.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

/**
 * @author: nile
 * @create: 2020-03-22 22:19
 * Jwt参数配置类
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    //自定义盐，整个jwt中的重要部分
    private String jwtSecret = "thisistest";
    //设置token过期时间ms
    private int jwtExpiration = 1000000;

    /**
     * 根据Authentication信息生成token
     * @param authentication
     */
    public String generateJwtToken(Authentication authentication) {

        User userPrincipal = new User();
        userPrincipal.setUserName(authentication.getName());

        return Jwts.builder()
		                .setSubject((userPrincipal.getUserName()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }

    /**
     * 验证当前token是否有效
     * @param authToken
     */
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

    /**
     * 根据token返回用户名
     * @param token
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
}