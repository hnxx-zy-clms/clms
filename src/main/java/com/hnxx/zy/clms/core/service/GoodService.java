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
    int getGoodCountForArticle(Integer uid, Integer aid);

    /**
     * 根据uid和cid查询点赞情况
     * @param cid
     * @param uid
     * @return
     */
    int getGoodCountForComment(Integer uid, Integer cid);

    /**
     * 根据uid和qid查询点赞情况
     * @param uid
     * @param qid
     * @return
     */
    int getGoodCountForQuestion(Integer uid, Integer qid);

    /**
     * 根据uid和aid查询点赞情况
     * @param uid
     * @param sid
     * @return
     */
    int getGoodCountForAnswer(Integer uid, Integer sid);

    /**
     * 根据uid和vid查询点赞情况
     * @param uid
     * @param vid
     * @return
     */
    int getGoodCountForVideo(Integer uid, Integer vid);
}
