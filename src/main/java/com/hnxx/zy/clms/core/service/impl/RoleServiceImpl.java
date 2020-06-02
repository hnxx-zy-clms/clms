package com.hnxx.zy.clms.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.Role;
import com.hnxx.zy.clms.core.mapper.RoleMapper;
import com.hnxx.zy.clms.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 南街北巷
 * @data 2020/6/3 4:09
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo getAllRole(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> list = roleMapper.getAllRole();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
