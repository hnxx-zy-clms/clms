package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.ClassSex;
import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.ClassesReport;

import java.util.List;

public interface ClassesService {
    void save(Classes classes);

    void updateClasses(int id , int type);

    PageInfo findAllClassesByPage(int page , int size,String name);

    void updateIdsClasses(List<Integer> ids);

    List<ClassesReport> report();

    List<ClassSex> findSexPercent();

    void deleteClassesById(int id);

    Classes findClassById(int id);

    List<Classes> findAll();

    void alter(Classes classes);
}
