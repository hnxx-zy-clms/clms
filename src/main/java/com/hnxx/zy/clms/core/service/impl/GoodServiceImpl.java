/**
 * @FileName: GoodServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/3/25 14:32
 * Description: 点赞业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Good;
import com.hnxx.zy.clms.core.mapper.GoodMapper;
import com.hnxx.zy.clms.core.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    /**
     * 点赞操作
     * @param good
     */
    @Override
    public void doGood(Good good) {
        // 获取用户id
        int uid = good.getUserId();
        // 获取文章id
        int aid = good.getArticleId();
        // 获取评论id
        int cid = good.getCommentId();

        // 判断是否为文章的点赞,如果文章的id,不等于0,表示文章的点赞
        if (aid != 0 && cid == 0){
            goodMapper.goodArticle(aid);
        }
        // 判断是否为评论的点赞,如果评论的id,不等于0,表示评论的点赞
        else if (cid != 0 && aid == 0){
            goodMapper.goodComment(cid);
        }
        goodMapper.save(good);
    }
}
