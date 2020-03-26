package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.College;

public interface CollegeService {

    void save(College college);

    void updateClasses(int id);

    PageInfo findAllByPage(int page , int size);

}
