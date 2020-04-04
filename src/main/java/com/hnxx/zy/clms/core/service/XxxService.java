/**
 * @FileName: XxxService
 * @Author: fusheng
 * @Date: 2020/3/17 13:37
 * Description: Xxx业务接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Xxx;

import java.util.List;

public interface XxxService {

    /**
     * 保存
     * @param xxx
     */
    void save(Xxx xxx);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 修改
     * @param xxx
     */
    void update(Xxx xxx);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Xxx getById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Xxx> getAll();

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Xxx> getByPage(Page<Xxx> page);

    /**
     * 根据id启用
     * @param id
     */
    void enableById(Integer id);

    /**
     * 根据id弃用
     * @param id
     */
    void disableById(Integer id);

}
