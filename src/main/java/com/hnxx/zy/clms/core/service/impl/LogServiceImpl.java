/**
 * @FileName: LogServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/3/18 11:08
 * Description: 日志业务接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.entity.Log;
import com.hnxx.zy.clms.core.mapper.LogMapper;
import com.hnxx.zy.clms.core.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public void save(Log logger) {
        logMapper.save(logger);
    }
}
