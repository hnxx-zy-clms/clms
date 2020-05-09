package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 南街北巷
 * @data 2020/5/9 9:26
 */
public class UserServiceImpl implements UserService {

    @Autowired()
    private UserMapper userMapper;

    @Override
    public Page<User> getByPage(Page<User> page) {
        return null;
    }

    @Override
    public List<User> getByName(String name) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteAllUser() {

    }

    @Override
    public void deleteUserById(Integer id) {

    }

    @Override
    public void updateUserById(User user) {

    }
}
