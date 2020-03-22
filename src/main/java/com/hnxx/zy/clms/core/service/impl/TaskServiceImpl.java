package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.entity.Task;
import com.hnxx.zy.clms.core.mapper.TaskMapper;
import com.hnxx.zy.clms.core.service.TaskService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 18:43
 * @version: 1.0
 * @desc:
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public void saveTask(Task task) {
        taskMapper.saveTask(task);
    }

    @Override
    public void saveReply(Task task) {
        taskMapper.saveReply(task);
    }

    @Override
    public List<Task> getAllListByUserId(Integer id) {
        return taskMapper.getAllListByUserId(id);
    }

    @Override
    public Task getTaskReply(Integer taskid, Integer userid) {
        return taskMapper.getTaskReply(taskid, userid);
    }
}
