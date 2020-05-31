package com.hnxx.zy.clms.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.entity.UserSearch;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * 新增用户(登录信息)
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserSearch getById(Integer id) {
        UserSearch user = userMapper.getById(id);
        return user;
    }

    /**
     * 根据用户id删除用户(将删除标识位置为1)
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOneById(Integer id) { ;
        userMapper.deleteOneById(id);
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
     * 根据用户id或用户名查询用户信息
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserSearch> getByGroup(User user) {
        List<UserSearch> users = userMapper.getByGroup(user);
        return users;
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

    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public User selectById(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public Integer selectUserId(String name) {
        return userMapper.selectUserId(name);
    }

    @Override
    public int getUserNum(Page<ReportStatistics> page) {
        return userMapper.getUserNum(page);
    }

    @Override
    public Integer[] getGroupIds(Page<ReportStatistics> page) {
        return userMapper.getGroupIds(page);
    }

    /**
     * 更新用户头像
     *
     * @param userId
     * @param userIcon
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserIconById(Integer userId, String userIcon) {
        userMapper.updateUserIconById(userId, userIcon);
    }

}
