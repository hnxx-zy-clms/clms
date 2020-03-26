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
import com.hnxx.zy.clms.core.mapper.CollectionMapper;
import com.hnxx.zy.clms.core.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    /**
     * 保存添加收藏
     * @param collection
     */
    @Override
    public void save(Collection collection) {
        collectionMapper.save(collection);
    }
}
