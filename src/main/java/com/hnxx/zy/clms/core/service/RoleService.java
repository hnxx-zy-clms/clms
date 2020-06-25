package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.Role;

/**
 * @author 南街北巷
 * @data 2020/6/3 4:06
 */
public interface RoleService {

    /**
     * 查询角色
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getAllRole(Integer pageNum, Integer pageSize);


    /**
     * 增加角色
     *
     * @return
     */
    void insertRole(Role role);

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    Role getById(Integer id);
}
