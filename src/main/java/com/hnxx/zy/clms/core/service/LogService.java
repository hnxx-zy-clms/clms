/**
 * @FileName: LogService
 * @Author: code-fusheng
 * @Date: 2020/3/18 11:07
 * Description: 日志业务接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Log;

import java.util.List;

public interface LogService {

    /**
     * 保存
     * @param logger
     */
    void save(Log logger);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Log> getByPage(Page<Log> page);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除[根据id集合删除]
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

}
