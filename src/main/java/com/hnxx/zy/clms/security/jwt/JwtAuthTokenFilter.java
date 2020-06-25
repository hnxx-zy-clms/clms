package com.hnxx.zy.clms.security.jwt;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.utils.RedisUtil;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.security.sms.ValidateCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: nile
 * @create: 2020-03-22 22:19
 * Jwt过滤器
 */

public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider tokenProvider;

    @Autowired
    UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)throws ServletException, IOException {
            try {
                //获取请求头中的token
                String jwt = getJwt(request);
                //判断token是否存在，并且验证该token是否有效
                if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
                    //获取token中的用户名
                    String username = tokenProvider.getUserNameFromJwtToken(jwt);
                    //调用userDetailsService进行验证，验证成功返回一个UserDetails
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    //根据UserDetails生成UsernamePasswordAuthenticationToken实例
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    //在UsernamePasswordAuthenticationToken实例中加入当前请求的详细信息
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //authentication写入Security的session中
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                logger.error(" Can NOT set user authentication -> Message: { } ", e);
            }
            //token不存在，进入Security的UsernameFilter验证流程
            filterChain.doFilter(request, response);

    }

    /**
     * 取得当前request,判断token是否存在，如果不存在返回null
     * @param request
     */
    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
        	return authHeader.replace("Bearer ","");
        }
        return null;
    }
}
