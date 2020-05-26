package com.hnxx.zy.clms.security.customauths;

import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author: nile
 * @create: 2020-03-22 22:19
 *Security 注册用户类
 */

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 添加认证信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        User user = userService.selectByName(username);
        // 判断用户是否存在
        if(user == null) {
                throw new UsernameNotFoundException("账号不存在");
        }
        // 添加权限
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getUserPositionId()));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getUserPassword(), authorities);
    }
}