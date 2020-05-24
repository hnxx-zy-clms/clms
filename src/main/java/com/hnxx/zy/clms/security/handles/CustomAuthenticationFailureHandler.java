package com.hnxx.zy.clms.security.handles;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.utils.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: nile
 * @create: 2020-03-22 22:19
*自定义 CustomAuthenticationFailureHandler 类来实现 AuthenticationFailureHandler 接口，用来处理认证失败后逻辑：
*onAuthenticationFailure()方法的第三个参数 exception 为认证失败所产生的异常，这里也是简单的返回到前台。
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new Result<>(401,exception.getMessage())));
    }
}