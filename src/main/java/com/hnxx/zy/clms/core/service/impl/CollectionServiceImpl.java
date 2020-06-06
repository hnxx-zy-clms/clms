/**
 * @FileName: ConnectionServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/3/26 11:11
 * Description: 收藏业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.Collection;
import com.hnxx.zy.clms.core.entity.Good;
import com.hnxx.zy.clms.core.mapper.ArticleMapper;
import com.hnxx.zy.clms.core.mapper.CollectionMapper;
import com.hnxx.zy.clms.core.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 保存添加收藏
     * @param collection
     */
    @Override
    public void save(Collection collection) {
        collectionMapper.save(collection);
    }

    /**
     * 用户文章是否收藏
     * @param aid
     * @param uid
     * @return
     */
    @Override
    public int getCollectionCountForArticle(Integer uid, Integer aid) {
        int count = 0;
        List<Collection> collections = collectionMapper.getCollectionCountForArticle(uid, aid);
        if(collections.size() == 0) {
            count = 0;
        }else {
            count = 1;
        }
        return count;
    }

    /**
     * 用户提问是否收藏
     * @param qid
     * @param uid
     * @return
     */
    @Override
    public int getCollectionCountForQuestion(Integer uid, Integer qid) {
        int count = 0;
        List<Collection> collections = collectionMapper.getCollectionCountForQuestion(uid, qid);
        if(collections.size() == 0) {
            count = 0;
        }else {
            count = 1;
        }
        return count;
    }

    /**
     * 用户视频是否收藏
     * @param vid
     * @param uid
     * @return
     */
    @Override
    public int getCollectionCountForVideo(Integer uid, Integer vid) {
        int count = 0;
        List<Collection> collections = collectionMapper.getCollectionCountForVideo(uid, vid);
        if(collections.size() == 0) {
            count = 0;
        }else {
            count = 1;
        }
        return count;
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        collectionMapper.delete(id);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Collection getById(Integer id) {
        return collectionMapper.getById(id);
    }

    /**
     * 用户文章收藏
     * @param aid
     * @param uid
     * @return
     */
    @Override
    public int getCollectionCount(Integer uid, Integer aid) {
        int count;
        List<Collection> collections = collectionMapper.getCollectionCount(uid, aid);
        if(collections.size() == 0) {
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
    public Page<Collection> getByPage(Page<Collection> page) {
        // 查询数据
        List<Collection> collectionList = collectionMapper.getByPage(page);
        page.setList(collectionList);
        // 查询总数
        int totalCount = collectionMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 根据用户id查询收藏列表
     * @param id
     * @return
     */
    @Override
    public List<Collection> getListByUserId(Integer id) {
        List<Collection> collectionList = collectionMapper.getListByUserId(id);
        return collectionList;
    }
}
