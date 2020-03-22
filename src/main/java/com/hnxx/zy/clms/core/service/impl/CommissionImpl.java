package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.entity.Commission;
import com.hnxx.zy.clms.core.mapper.CommissionMapper;
import com.hnxx.zy.clms.core.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 12:52
 * @version: 1.0
 * @desc:
 */
@Service
public class CommissionImpl implements CommissionService {
    @Autowired
    private CommissionMapper commissionMapper;

    @Override
    public void saveCommission(Commission commission) {
        commissionMapper.saveCommission(commission);
    }

    @Override
    public void setDid(Integer id) {
        commissionMapper.setDid(id);
    }

    @Override
    public void deleteCom(Integer id) {
        commissionMapper.deletecom(id);
    }

    @Override
    public List<Commission> getComByIdAndTime(Integer id, String time) {
        return commissionMapper.getComByIdAndTime(id, time);
    }


}
