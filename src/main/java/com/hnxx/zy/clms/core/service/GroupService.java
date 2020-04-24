package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.College;
import com.hnxx.zy.clms.core.entity.Group;

import java.util.List;

public interface GroupService {

    void save(Group group);

    void updateClasses(int id, int type);

    PageInfo findAllByPage(int page , int size);

    void updateIdsClasses(List<Integer> ids);
}
