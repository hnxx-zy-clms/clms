package com.hnxx.zy.clms.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.College;
import com.hnxx.zy.clms.core.mapper.CollegeMapper;
import com.hnxx.zy.clms.core.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public void save(College college) {
        college.setCollegeStates(1);
        collegeMapper.save(college);
    }

    @Override
    public void updateClasses(int id , int type) {
        switch (type){
            case 0:
                collegeMapper.disableClasses(id);
                break;
            case 1:
                collegeMapper.enableClasses(id);
                break;
        }
    }

    @Override
    public PageInfo findAllByPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<College> list = collegeMapper.findAllByPage();
        PageInfo info = new PageInfo(list);
        return info;
    }

    @Override
    public void updateIdsClasses(List<Integer> ids) {
        for (Integer id : ids){
            collegeMapper.disableClasses(id);
        }
    }

    @Override
    public List<College> findAll() {
        List<College> colleges = collegeMapper.findAllByPage();
        return colleges;
    }
}
