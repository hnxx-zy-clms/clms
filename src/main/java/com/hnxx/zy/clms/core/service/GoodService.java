/**
 * @FileName: GoodService
 * @Author: code-fusheng
 * @Date: 2020/3/25 14:32
 * Description: 点赞业务逻辑接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Good;

import java.util.List;

public interface GoodService {

    /**
     * 点赞
     * @param good
     */
     void doGood(Good good);


    /**
     * 根据用户id获取点赞集合
     * @param id
     * @return
     */
    List<Good> getListByUserId(Integer id);
}
