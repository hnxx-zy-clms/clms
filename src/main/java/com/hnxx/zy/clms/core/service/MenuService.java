package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Menu;

/**
 * @program: clms
 * @description: MenuService
 * @author: nile
 * @create: 2020-05-05 16:31
 **/
public interface MenuService {
    Menu[] getMenuByRole(Integer role);
}
