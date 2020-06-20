package com.hnxx.zy.clms.security.filter;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.utils.RedisUtil;
import com.hnxx.zy.clms.common.utils.Result;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: clms
 * @description: 监听访问IP地址，并进行相关逻辑判断
 * @author: nile
 * @create: 2020-06-20 16:17
 **/
public class IpFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest,@NonNull HttpServletResponse httpServletResponse,@NonNull FilterChain filterChain) throws ServletException, IOException {
        String ip = httpServletRequest.getHeader("x-forwarded-for");
        // 判断该IP是否被封禁
        if (redisUtil.get("rf" + ip) != null) {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(new Result<>(401, "由于您近期存在异常操作，系统已禁止您访问。")));
        } else {
                //IP地址判断通过，进入Security的UsernameFilter验证流程
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
    }

}
