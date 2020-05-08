package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.entity.GithubCount;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.core.service.GithubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 南街北巷
 * @data 2020/5/2 19:48
 */
@Service
public class GithubUserServiceImpl implements GithubUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public GithubCount getById(Integer id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(GithubCount githubCount) {
        userMapper.insert(githubCount);
    }

    @Override
    public void deleteAllUser() {

    }

    @Override
    public void deleteUserById(int id) {

    }
}
