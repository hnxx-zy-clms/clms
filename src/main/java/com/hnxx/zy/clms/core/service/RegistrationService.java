package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/19 13:03
 * @version: 1.0
 * @desc:
 */

public interface RegistrationService {

    /**
     * 新建签到
     *
     * @param registration
     */
    void saveRegist(Registration registration);

    /**
     * 获取用户id签到情况
     *
     * @param id
     * @return
     */
    List<Registration> getRegisListById(Integer id);

    /**
     * 获取当天签到情况
     *
     * @param date
     * @return
     */
    List<Registration> getRegisListByDate(String date);

    /**
     * 取消签到
     *
     * @param id
     */
    void deleteRegis(Integer id);

}
