package com.hnxx.zy.clms.security.test.services.Impl;

import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.security.test.entity.SysUser;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: news
 * @description: 测试security用户服务层实现类
 * @author: nile
 * @create: 2020-03-22 22:19
 **/
@Service
public class UserSeviceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public SysUser selectByName(String username) {
        return userMapper.selectByName(username);
    }
}
