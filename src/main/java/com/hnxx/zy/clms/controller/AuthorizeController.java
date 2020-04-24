package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.core.entity.GithubCount;
import com.hnxx.zy.clms.core.entity.GithubCountParameterFactory;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.dto.AccessTokenDTO;
import com.hnxx.zy.clms.dto.GithubUser;
import com.hnxx.zy.clms.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 南街北巷
 * @data 2020/4/11 14:56
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private GithubCountParameterFactory githubCountParameterFactory;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){//拿到session
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(githubCountParameterFactory.getClientId());
        accessTokenDTO.setClient_secret(githubCountParameterFactory.getClientSecret());
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(githubCountParameterFactory.getRedirectUri());
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        if(githubUser!=null){
            //登录成功
            //获取用户信息,并且插入到数据库中
            GithubCount githubCount = new GithubCount();
            String token = UUID.randomUUID().toString();
            githubCount.setToken(token);
            githubCount.setName(githubUser.getName());
            githubCount.setAccountId(String.valueOf(githubUser.getId()));
            //获取系统时间
            Date date = new Date();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            //转换为日期格式的字符串
            String now = sd.format(date);
            System.out.println(now);
            githubCount.setCreatedTime(date);
            githubCount.setUpdateTime(githubCount.getCreatedTime());
            userMapper.insert(githubCount);
            request.getSession().setAttribute("user", githubUser);
//            System.out.println(githubCountFactory.getClientId());测试yml自定义参数传值
            return "redirect:/home";
        } else {
            //登录失败
            return "redirect:/login";
        }
    }
}
