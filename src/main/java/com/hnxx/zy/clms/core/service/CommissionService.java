package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Commission;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 12:51
 * @version: 1.0
 * @desc:
 */
public interface CommissionService {
    /**
     * 新增代办
     *
     * @param commission
     */
    void saveCommission(Commission commission);

    /**
     * 修改代办状态
     *
     * @param id
     */
    void setDid(Integer id);

    /**
     * 删除代办
     *
     * @param id
     */
    void deleteCom(Integer id);

    /**
     * 根据日期和用户id来查询代办
     *
     * @param id
     * @param time
     * @return
     */
    List<Commission> getComByIdAndTime(Integer id, String time);


}
