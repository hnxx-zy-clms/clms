package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.College;

import java.util.List;

public interface CollegeService {

    void save(College college);

    void updateClasses(int id,int type);

    PageInfo findAllByPage(int page , int size ,String collegeName);

    void updateIdsClasses(List<Integer> ids);

    List<College> findAll();

    void deleteClassesById(int id);

    College findClassById(int id);

    void alter(College college);
}
