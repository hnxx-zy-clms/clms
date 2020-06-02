package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;

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
}
