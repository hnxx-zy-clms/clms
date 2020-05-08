package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.entity.Menu;
import com.hnxx.zy.clms.core.mapper.MenuMapper;
import com.hnxx.zy.clms.core.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: clms
 * @description: MenuServiceImpl
 * @author: nile
 * @create: 2020-05-05 16:32
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public Menu[] getMenuByRole(Integer role) {
        return menuMapper.getMenuByRole(role);
    }
}
