package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * 更新签到
     *
     * @param sign_class
     */
    void updateRegistration(String sign_class, Integer sign_id);

    /**
     * 获取当天签到的情况
     *
     * @param user_id
     * @param sign_time
     * @return
     */
    Registration selectSignClass(Integer user_id, Date sign_time);

    /**
     * 获取所有用户本周签到
     *
     * @return
     */
    List<Registration> getRegisList();

    /**
     * 获取当天当节课签到人姓名
     *
     * @param classes
     * @param date
     * @return
     */
    List<Registration> getNameList(Integer classes, String date);

}
