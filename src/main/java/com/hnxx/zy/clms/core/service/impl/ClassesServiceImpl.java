package com.hnxx.zy.clms.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.ClassSex;
import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.ClassesReport;
import com.hnxx.zy.clms.core.mapper.ClassesMapper;
import com.hnxx.zy.clms.core.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public void save(Classes classes) {
        classes.setClassesStates(1);
        classesMapper.save(classes);
    }

    @Override
    public void updateClasses(int id , int type) {
        switch (type){
            case 0:
                classesMapper.disableClasses(id);
                break;
            case 1:
                classesMapper.enableClasses(id);
                break;
        }
    }

    @Override
    public PageInfo findAllClassesByPage(int page, int size,String name) {
        PageHelper.startPage(page, size);
        List<Classes> list = classesMapper.findAllClassesByPage(name);
        PageInfo info = new PageInfo(list);
        return info;
    }

    @Override
    public void updateIdsClasses(List<Integer> ids) {
        for (Integer id : ids){
            classesMapper.disableClasses(id);
        }
    }

    @Override
    public List<ClassesReport> report() {
        List<ClassesReport> classesReportList = classesMapper.report();
        return classesReportList;
    }

    @Override
    public List<ClassSex> findSexPercent() {
        List<ClassSex> classSexList = classesMapper.findSexPercent();
        return classSexList;
    }

    @Override
    public void deleteClassesById(int id) {
        classesMapper.deleteClassesById(id);
    }

    @Override
    public Classes findClassById(int id) {
        Classes classes = classesMapper.findClassById(id);
        return classes;
    }

    @Override
    public List<Classes> findAll() {
        return classesMapper.findAll();
    }

    @Override
    public void alter(Classes classes) {
        classesMapper.alter(classes);
    }
}
