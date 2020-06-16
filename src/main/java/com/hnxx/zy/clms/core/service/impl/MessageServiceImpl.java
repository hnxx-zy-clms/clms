/**
 * @FileName: MessageServiceImpl
 * @Author: fusheng
 * @Date: 2020/3/17 13:43
 * Description: 业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Message;
import com.hnxx.zy.clms.core.mapper.MessageMapper;
import com.hnxx.zy.clms.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1. 统一在业务逻辑接口出标注 @Service
 *  * 2. 在 ServiceImpl 业务逻辑接口实现类的方法前面不需要加注释
 *  * @author fusheng
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void save(Message message) {
        messageMapper.save(message);
    }

    @Override
    public void deleteById(Integer id) {
        messageMapper.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        messageMapper.deleteByIds(ids);
    }

    @Override
    public void update(Message message) {
        messageMapper.update(message);
    }

    @Override
    public Message getById(Integer id) {
        return messageMapper.getById(id);
    }

    @Override
    public List<Message> getAll() {
        return messageMapper.getAll();
    }

    @Override
    public Page<Message> getByPage(Page<Message> page) {
        // 查询数据
        List<Message> messageList = messageMapper.getByPage(page);
        page.setList(messageList);
        // 查询总数
        int totalCount = messageMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<Message> getList(String user) {
        return messageMapper.getListByUser(user);
    }
}
