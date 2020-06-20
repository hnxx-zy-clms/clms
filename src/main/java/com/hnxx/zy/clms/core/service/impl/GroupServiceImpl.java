package com.hnxx.zy.clms.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.College;
import com.hnxx.zy.clms.core.entity.Group;
import com.hnxx.zy.clms.core.mapper.GroupMapper;
import com.hnxx.zy.clms.core.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public void save(Group group) {
        group.setGroupStates(1);
        groupMapper.save(group);
    }

    @Override
    public void updateClasses(int id ,int type) {
        switch (type){
            case 0:
                groupMapper.disableClasses(id);
                break;
            case 1:
                groupMapper.enableClasses(id);
                break;
        }
    }

    @Override
    public PageInfo findAllByPage(int page, int size,String groupName) {
        PageHelper.startPage(page, size);
        List<Group> list = groupMapper.findAllByPage(groupName);
        PageInfo info = new PageInfo(list);
        return info;
    }

    @Override
    public void updateIdsClasses(List<Integer> ids) {
        for (Integer id : ids){
            groupMapper.disableClasses(id);
        }
    }

    @Override
    public void deleteClassesById(int id) {
        groupMapper.deleteClassesById(id);
    }

    @Override
    public Group findClassById(int id) {
        Group group = groupMapper.findClassById(id);
        return group;
    }

    @Override
    public List<Group> findAll() {
        return groupMapper.findAll();
    }

    @Override
    public void alter(Group group) {
        groupMapper.alter(group);
    }
}
