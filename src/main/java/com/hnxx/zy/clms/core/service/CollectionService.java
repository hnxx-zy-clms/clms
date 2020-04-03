/**
 * @FileName: ConnectionService
 * @Author: code-fusheng
 * @Date: 2020/3/26 11:11
 * Description: 收藏业务逻辑接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Collection;

public interface CollectionService {

    /**
     * 保存添加 收藏
     * @param collection
     */
    void save(Collection collection);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);
}
