package com.hnxx.zy.clms.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.Classes;
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
    public PageInfo findAllClassesByPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<Classes> list = classesMapper.findAllClassesByPage();
        PageInfo info = new PageInfo(list);
        return info;
    }

    @Override
    public void updateIdsClasses(List<Integer> ids) {
        for (Integer id : ids){
            classesMapper.disableClasses(id);
        }
    }
}
