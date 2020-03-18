/**
 * @FileName: LogService
 * @Author: code-fusheng
 * @Date: 2020/3/18 11:07
 * Description: 日志业务接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Log;

public interface LogService {

    /**
     * 保存
     * @param logger
     */
    void save(Log logger);
}
