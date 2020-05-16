package com.hnxx.zy.clms.security.sms;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: test_security
 * @description: 短信验证码过滤器
 * @author: nile
 * @create: 2020-05-15 14:20
 **/
public class SmsValidateCodeFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_MOBILE = "mobile";

    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE;
    private boolean postOnly = true;


    public SmsValidateCodeFilter() {
        super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String mobile = obtainMobile(request);

        if (mobile == null) {
            mobile = "";
        }
        mobile = mobile.trim();
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
        this.setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    /**
     * 获取手机号
     * @param request
     * @return
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }


    protected void setDetails(HttpServletRequest request,
                              SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "参数不能为  or null");
        this.mobileParameter = mobileParameter;
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getMobileParameter() {
        return mobileParameter;
    }
}