package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Todo;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 12:51
 * @version: 1.0
 * @desc:
 */
public interface TodoService {
    /**
     * 新增代办
     *
     * @param todo
     */
    void saveTodo(Todo todo);

    /**
     * 修改代办状态
     *
     * @param id
     */
    void setDid(Integer id);

    /**
     * 删除代办
     *
     * @param id
     */
    void deleteTodo(Integer id);

    /**
     * 根据日期和用户id来查询代办
     *
     * @param id
     * @param time
     * @return
     */
    List<Todo> getTodoByIdAndTime(Integer id, String time);


}
