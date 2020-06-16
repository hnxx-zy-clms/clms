package com.hnxx.zy.clms.security.sms;

import com.hnxx.zy.clms.common.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: test_security
 * @description: ValidateCodeFilter
 * @author: nile
 * @create: 2020-05-15 16:49
 **/
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse,@NonNull FilterChain filterChain)
            throws ServletException, IOException {
        boolean red = StringUtils.equals("/authentication/mobile", httpServletRequest.getRequestURI())||StringUtils.equals("/register", httpServletRequest.getRequestURI());
        if(red && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")){
            try {
                validateSmsCode(httpServletRequest);
                redisUtil.del(httpServletRequest.getParameter("mobile"));
            }catch (ValidateCodeException e) {
               authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    /**
     * 校验手机验证码
     */
    private void validateSmsCode(HttpServletRequest request) {
        //请求里的手机号和验证码
        String mobileInRequest = request.getParameter("mobile");
        String codeInRequest = request.getParameter("smsCode");

        SmsCode code = (SmsCode)redisUtil.get(mobileInRequest);

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if(code == null){
            throw new ValidateCodeException("验证码已失效");
        }

        if(!StringUtils.equals(code.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

}