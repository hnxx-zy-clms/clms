package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.College;
import com.hnxx.zy.clms.core.entity.Group;

public interface GroupService {

    void save(Group group);

    void updateClasses(int id);

    PageInfo findAllByPage(int page , int size);

}
