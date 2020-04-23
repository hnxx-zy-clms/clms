package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.Classes;

import java.util.List;

public interface ClassesService {
    void save(Classes classes);

    void updateClasses(int id , int type);

    PageInfo findAllClassesByPage(int page , int size);

    void updateIdsClasses(List<Integer> ids);
}
