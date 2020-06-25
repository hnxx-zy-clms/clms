package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.core.entity.Position;

import java.util.List;

public interface PositionService {

    void save(Position position);

    void updateClasses(int id , int type);

    PageInfo findAllByPage(int page , int size,String positionName);

    void updateIdsClasses(List<Integer> ids);

    void deleteClassesById(int id);

    Position findClassById(int id);

    List<Position> findAll();

    void alter(Position position);
}
