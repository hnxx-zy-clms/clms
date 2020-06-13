/**
 * @FileName: messageService
 * @Author: fusheng
 * @Date: 2020/3/17 13:37
 * Description: message业务接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Message;

import java.util.List;

public interface MessageService {

    /**
     * 保存
     * @param message
     */
    void save(Message message);

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
     * @param message
     */
    void update(Message message);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Message getById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Message> getAll();

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Message> getByPage(Page<Message> page);

    /**
     * 查询当前用户的消息
     * @param user
     * @return
     */
    List<Message> getList(String user);
}
