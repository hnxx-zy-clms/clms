package com.hnxx.zy.clms.security.handles;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.security.jwt.JwtProvider;
import com.hnxx.zy.clms.security.test.entity.SysUser;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: nile
 * @create: 2020-03-22 22:19
 *自定义 CustomAuthenticationSuccessHandler 类来实现 AuthenticationSuccessHandler 接口，用来处理认证成功后逻辑：
 *onAuthenticationSuccess() 方法返回认证成功信息。
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String jwtToken = jwtProvider.generateJwtToken(authentication);
        SysUser user = userMapper.selectByName(authentication.getName());
        response.getWriter().write(JSON.toJSONString(new Result<Object>(jwtToken,user.getUserName())));
    }
}