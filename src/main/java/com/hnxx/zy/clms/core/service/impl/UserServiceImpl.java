package com.hnxx.zy.clms.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.entity.UserSearch;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 根据用户id或用户名查询组名和用户信息
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserSearch> getByGroup(User user) {
        List<UserSearch> group = userMapper.getByGroup(user);
        return group;
    }

    /**
     * 获取用户详细信息(分页)
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo getUserByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserSearch> list = userMapper.getUserByPage();
        PageInfo pageInfo =new PageInfo<>(list);
        return pageInfo;
    }
}
