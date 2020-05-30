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
        goodMapper.save(good);
    }

    /**
     * 根据id获取用户点赞集合
     * @param id
     * @return
     */
    @Override
    public List<Good> getListByUserId(Integer id) {
        List<Good> goodList = goodMapper.getListByUserId(id);
        return goodList;
    }

    /**
     * 用户文章点赞
     * @param aid
     * @param uid
     * @return
     */
    @Override
    public int getGoodCount(Integer uid, Integer aid) {
        int count = 0;
        List<Good> goods = goodMapper.getGoodCount(uid, aid);
        if(goods.size() == 0) {
            count = 0;
        }else {
            count = 1;
        }
        return count;
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

    /**
     * 取消点赞
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        goodMapper.deleteById(id);
    }



}
