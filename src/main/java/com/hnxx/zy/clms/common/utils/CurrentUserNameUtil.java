package com.hnxx.zy.clms.common.utils;

import com.hnxx.zy.clms.common.config.WebSocketEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * @author 黑鲨
 * @date 2020/3/25 23:25
 */

public class CurrentUserNameUtil {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    /**
     * 获取当前  登录在security框架中的用户名
     * @return
     */
    public  String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            authorities.forEach((a)->
                    logger.info(a.toString())
            );
            return currentUserName;
        }
        return null;
    }


}
