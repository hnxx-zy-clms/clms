package com.hnxx.zy.clms.security.test.services.Impl;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User selectByName(String username) {
        return userMapper.selectByName(username);
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

    /**
     * 根据用户id或用户名查询组名
     *
     * @param user
     * @return
     */
    @Override
    public String getByGroup(User user) {
        String groupName = userMapper.getByGroup(user);
        return groupName;
    }

}
