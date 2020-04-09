/**
 * @FileName: GoodServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/3/25 14:32
 * Description: 点赞业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.exception.ClmsException;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Good;
import com.hnxx.zy.clms.core.mapper.GoodMapper;
import com.hnxx.zy.clms.core.service.GoodService;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    /**
     * 点赞操作
     *
     * @param good
     */
    @Override
    public void doGood(Good good) {
        // 获取用户id
        int uid = good.getUserId();
        List<Good> goodList = goodMapper.getListByUserId(uid);
        // 判断是否为文章的点赞 判断是否为评论的点赞
        if (good.getArticleId() != null) {
            // 获取文章id
            int aid = good.getArticleId();
            for (Good oldGood : goodList) {
                if(oldGood.getArticleId() == null){
                    continue;
                }else {
                    if (uid == oldGood.getUserId() && aid == oldGood.getArticleId()) {
                        throw new ClmsException("重复文章点赞!");
                    }
                }
            }
            goodMapper.goodArticle(aid);
        } else if (good.getCommentId() != null) {
            // 获取评论id
            int cid = good.getCommentId();
            for (Good oldGood : goodList) {
                if(oldGood.getCommentId() == null){
                    continue;
                }else {
                    if (uid == oldGood.getUserId() && cid == oldGood.getCommentId()) {
                        throw new ClmsException("重复评论点赞!");
                    }
                }
            }
            goodMapper.goodComment(cid);
        }
        goodMapper.save(good);
    }

    /**
     * 根据id获取用户点赞集合
     *
     * @param id
     * @return
     */
    @Override
    public List<Good> getListByUserId(Integer id) {
        List<Good> goodList = goodMapper.getListByUserId(id);
        return goodList;
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public Page<Good> getByPage(Page<Good> page) {
        // 查询数据
        List<Good> goodList = goodMapper.getByPage(page);
        page.setList(goodList);
        // 统计总数
        int totalCount = goodMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

}
