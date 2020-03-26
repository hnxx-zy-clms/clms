package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.Position;

public interface PositionService {

    void save(Position position);

    void updateClasses(int id);

    PageInfo findAllByPage(int page , int size);
}
