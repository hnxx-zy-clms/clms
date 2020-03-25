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
        classesMapper.save(classes);
    }

    @Override
    public void updateClasses(int id) {
        classesMapper.updateClasses(id);
    }

    @Override
    public PageInfo findAllClassesByPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<Classes> list = classesMapper.findAllClassesByPage();
        PageInfo info = new PageInfo(list);
        return info;
    }
}
