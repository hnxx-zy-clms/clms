package com.hnxx.zy.clms.security.test.services;


import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
import com.hnxx.zy.clms.core.entity.User;

/**
 * @description: 测试security用户服务层接口
 * @author: nile
 * @create: 2020-03-22 22:19
 * @version: 1.0
 */
public interface UserService {

    /**
     * 打印测试用户信息
     */
    User selectByName(String username);

    /**
     * 获取登录用户名
     * @return
     */
    String getUserName();

    /**
     * 获取登录用户名
     * @return
     */
    Integer selectUserId(String name);
    /**
     * 获取班级、组人数
     * @return
     */
    int getUserNum(Page<ReportStatistics> page);

    /**
     *获取班级各组组名
     * @param page
     * @return
     */
    Integer[] getGroupIds(Page<ReportStatistics> page);

    /**
     * 用户更新头像
     * @param userId
     * @param userIcon
     */
    void updateUserIconById(Integer userId,String userIcon);

}
