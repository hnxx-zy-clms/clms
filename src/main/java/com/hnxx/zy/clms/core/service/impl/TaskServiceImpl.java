package com.hnxx.zy.clms.core.service.impl;

import afu.org.checkerframework.checker.oigj.qual.O;
import com.hnxx.zy.clms.common.utils.Page;
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
    public Task getTaskReply(Integer taskid, Integer userid) {
        return taskMapper.getTaskReply(taskid, userid);
    }

    @Override
    public Page<Task> getAllTaskByPage(Page<Task> task) {
        List<Task> tasks = taskMapper.getAllTaskByPage(task);
        task.setList(tasks);

        int totalCount = taskMapper.getCountByPage(task);
        task.setTotalCount(totalCount);
        return task;

    }

    @Override
    public Page<Task> getTaskSituation(Page<Task> task, Integer id) {
        List<Task> tasks = taskMapper.getTaskSituation(task, id);
        task.setList(tasks);

        int totalCount = taskMapper.getSituationCountByPage();
        task.setTotalCount(totalCount);
        return task;
    }

    @Override
    public void deleteTask(Integer id) {
        taskMapper.deleteTask(id);
    }

    @Override
    public Page<Task> getByPage(Page<Task> task, Integer id) {
        List<Task> tasks = taskMapper.getByPage(task, id);
        task.setList(tasks);

        int totalCount = taskMapper.getCountByPage(task);
        task.setTotalCount(totalCount);
        return task;
    }

    @Override
    public Page<Task> getByPageAdmin(Page<Task> page) {
        List<Task> tasks = taskMapper.getByPageAdmin(page);
        page.setList(tasks);

        int totalCount = taskMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
