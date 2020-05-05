package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.entity.Menu;
import com.hnxx.zy.clms.core.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuServiceImplTest {

    @Autowired
    private MenuService menuService;

    @Test
    void getMenuByRole() {
        Menu[] menus=menuService.getMenuByRole(1);
        System.out.println(menus[0].getPath());
    }
}