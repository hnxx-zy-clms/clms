package com.hnxx.zy.clms.security.test.services;


import com.hnxx.zy.clms.security.test.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * @description: 测试security用户服务层接口
 * @author: nile
 * @create: 2020-03-22 22:19
 * @version: 1.0
 */
public interface UserService {

    /**
     * 打印测试用户信息
     */
    SysUser selectByName(String username);

}
