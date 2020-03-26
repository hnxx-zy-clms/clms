package com.hnxx.zy.clms.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.Group;
import com.hnxx.zy.clms.core.entity.Position;
import com.hnxx.zy.clms.core.mapper.GroupMapper;
import com.hnxx.zy.clms.core.mapper.PositionMapper;
import com.hnxx.zy.clms.core.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public void save(Position position) {
        position.setPositionStatus(1);
        positionMapper.save(position);
    }

    @Override
    public void updateClasses(int id) {
        positionMapper.updateClasses(id);
    }

    @Override
    public PageInfo findAllByPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<Position> list = positionMapper.findAllByPage();
        PageInfo info = new PageInfo(list);
        return info;
    }
}
