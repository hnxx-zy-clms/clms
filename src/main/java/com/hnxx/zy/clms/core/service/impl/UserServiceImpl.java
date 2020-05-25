package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 南街北巷
 * @data 2020/5/9 9:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
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

    /**
     * 根据用户id查询组Id
     * @param id
     * @return
     */
    @Override
    public String selectByGroupId(Integer id) {
        String groupId = userMapper.selectByGroupId(id);
        return groupId;
    }

    /**
     * 根据用户id或用户名查询组名
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> getByGroup(User user) {
        List<User> group = userMapper.getByGroup(user);
        return group;
    }
}
