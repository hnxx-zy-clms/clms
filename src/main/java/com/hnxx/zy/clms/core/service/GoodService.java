/**
 * @FileName: GoodService
 * @Author: code-fusheng
 * @Date: 2020/3/25 14:32
 * Description: 点赞业务逻辑接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
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

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Good> getByPage(Page<Good> page);

    /**
     * 取消点赞
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据uid和aid查询点赞情况
     * @param aid
     * @param uid
     * @return
     */
    int getGoodCount(Integer uid, Integer aid);
}
