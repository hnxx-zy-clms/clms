package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.entity.Todo;
import com.hnxx.zy.clms.core.mapper.TodoMapper;
import com.hnxx.zy.clms.core.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 12:52
 * @version: 1.0
 * @desc:
 */
@Service
public class TodoImpl implements TodoService {
    @Autowired
    private TodoMapper todoMapper;

    @Override
    public void saveTodo(Todo todo) {
        todoMapper.saveTodo(todo);
    }

    @Override
    public void setDid(Integer id) {
        todoMapper.setDid(id);
    }

    @Override
    public void deleteTodo(Integer id) {
        todoMapper.deleteTodo(id);
    }

    @Override
    public List<Todo> getTodoByIdAndTime(Integer id, String time) {
        return todoMapper.getTodoByIdAndTime(id, time);
    }


}
