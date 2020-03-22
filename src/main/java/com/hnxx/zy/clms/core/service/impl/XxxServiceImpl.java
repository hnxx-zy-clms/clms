/**
 * @FileName: XxxServiceImpl
 * @Author: fusheng
 * @Date: 2020/3/17 13:43
 * Description: 业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.entity.Xxx;
import com.hnxx.zy.clms.core.mapper.XxxMapper;
import com.hnxx.zy.clms.core.service.XxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1. 统一在业务逻辑接口出标注 @Service
 *  * 2. 在 ServiceImpl 业务逻辑接口实现类的方法前面不需要加注释
 *  * @author fusheng
 */

@Service
public class XxxServiceImpl implements XxxService {

    @Autowired
    private XxxMapper xxxMapper;

    @Override
    public List<Xxx> getAll() {
        return xxxMapper.getAll();
    }

    @Override
    public void save(Xxx xxx) {
        xxxMapper.save(xxx);
    }

    @Override
    public void update(Xxx xxx) {
        xxxMapper.update(xxx);
    }

    @Override
    public Xxx getById(Integer id) {
        return xxxMapper.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        xxxMapper.deleteById(id);
    }
}
