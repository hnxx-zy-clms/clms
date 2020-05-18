/**
 * @FileName: ConnectionService
 * @Author: code-fusheng
 * @Date: 2020/3/26 11:11
 * Description: 收藏业务逻辑接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Collection;

import java.util.List;

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

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Collection getById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Collection> getByPage(Page<Collection> page);

    /**
     * 根据用户id查询收藏列表
     * @param id
     * @return
     */
    List<Collection> getListByUserId(Integer id);

    /**
     * 根据uid和aid查询收藏情况
     * @param aid
     * @param uid
     * @return
     */
    int getCollectionCount(Integer aid, Integer uid);
}
