package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Todo;
import com.hnxx.zy.clms.core.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 12:56
 * @version: 1.0
 * @desc:
 */
@RestController
@RequestMapping("/todo")
public class TodoController {
    private static Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    /**
     * 保存今日代办
     *
     * @param todo
     * @return
     */
    @PostMapping("/saveTodo")
    public Result saveTodo(@RequestBody Todo todo) {
        todoService.saveTodo(todo);
        return new Result("保存成功");
    }

    /**
     * 将代办状态设置为已完成
     *
     * @param id
     * @return
     */
    @PutMapping("/setIsDo/{id}")
    public Result setIsDo(@PathVariable("id") Integer id) {
        todoService.setDid(id);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 删除今日代办
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteTodo/{id}")
    public Result deleteTodo(@PathVariable("id") Integer id) {
        todoService.deleteTodo(id);
        return new Result("删除成功");
    }

    /**
     * 根据用户id获取指定日期的今日代办
     *
     * @param id
     * @param time
     * @return
     */
    @GetMapping("/getTodoByIdAndTime/{id}/{time}")
    public Result getTodoByIdAndTime(@PathVariable("id") Integer id, @PathVariable("time") String time) {
        List<Todo> todos = todoService.getTodoByIdAndTime(id, time);
        return new Result<>(todos);
    }


}
