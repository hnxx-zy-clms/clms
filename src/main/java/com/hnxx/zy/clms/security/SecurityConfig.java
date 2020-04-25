package com.hnxx.zy.clms.security;

import com.hnxx.zy.clms.provider.GithubProvider;
import com.hnxx.zy.clms.security.customauths.CustomUserDetailsService;
import com.hnxx.zy.clms.security.handles.CustomAuthenticationFailureHandler;
import com.hnxx.zy.clms.security.handles.CustomAuthenticationSuccessHandler;
import com.hnxx.zy.clms.security.jwt.JwtAuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

/**
 * @program: news
 * @description: Security主配置类
 * @author: nile
 * @create: 2020-03-22 21:14
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 配置Nginx部署代理是请求参数特殊字符问题
     * @return
     */
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    /**
     * 注入jwt
     * @return
     */
    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    /**
     * 自定义DaoAuthenticationProvider
     * @return
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/login","/","/register","/home","/callback").permitAll()
                .anyRequest()
//                允许所有请求通过(开发测试时设置，不设置登录测试要抓狂)
//                .permitAll()
                //坑爹de认证方法
                .authenticated()
                .and()
                //设置登录方式,和登录接口，登录请求方式必须是Post
                .formLogin().loginPage("/login")

                // 自定义登录成功和失败处理
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
//                把jwt加到security过滤器链中
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                //禁止security自己创建session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 关闭CSRF跨域,开发和测试时较方便，上线时需要开启
       http.cors().and().csrf().disable();

    }

    @Override
    public void configure(WebSecurity web){
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**,/static/**","/templates/**","/img/**");
    }
}
