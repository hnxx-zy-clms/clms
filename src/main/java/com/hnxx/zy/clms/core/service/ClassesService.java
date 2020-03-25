package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.Classes;

public interface ClassesService {
    void save(Classes classes);

    void updateClasses(int id);

    PageInfo findAllClassesByPage(int page , int size);
}
