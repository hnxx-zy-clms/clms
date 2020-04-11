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
    @Transactional(rollbackFor = Exception.class)
    public void save(Collection collection) {
        collectionMapper.save(collection);
        // 获取收藏的文章id
        int aid = collection.getArticleId();
        // 根据文章id查询文章的收藏量
        int cCount = collectionMapper.getCollectionCountByAid(aid);
        // 更新文章收藏量
        articleMapper.updateCollectionCount(cCount, aid);

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
