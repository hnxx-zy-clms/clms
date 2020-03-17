/**
 * @FileName: XxxService
 * @Author: fusheng
 * @Date: 2020/3/17 13:37
 * Description: Xxx业务接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Xxx;

public interface XxxService {

    /**
     * 保存
     * @param xxx
     */
    void save(Xxx xxx);

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
}
