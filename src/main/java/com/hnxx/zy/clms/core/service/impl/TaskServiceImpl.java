package com.hnxx.zy.clms.core.service.impl;

import afu.org.checkerframework.checker.oigj.qual.O;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Task;
import com.hnxx.zy.clms.core.entity.TaskUser;
import com.hnxx.zy.clms.core.mapper.TaskMapper;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.core.service.TaskService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveTask(Task task) {
        taskMapper.saveTask(task);
    }

    @Override
    public void saveReply(TaskUser taskUser) {
        taskMapper.saveReply(taskUser);
    }

    @Override
    public TaskUser getTaskReply(Integer taskid, Integer userid) {
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
    public Page<TaskUser> getTaskSituation(Page<TaskUser> taskuser, Integer id) {
        List<TaskUser> tasks = taskMapper.getTaskSituation(taskuser, id);
        taskuser.setList(tasks);

        int totalCount = userMapper.selectUserNum();
        taskuser.setTotalCount(totalCount);
        return taskuser;
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

    @Override
    public void savedTopushed(Integer id, Date date) {
        taskMapper.savedTopushed(id, date);
    }

    @Override
    public void deleteTasks(Integer[] params) {
        taskMapper.deleteTasks(params);
    }

    @Override
    public void delete(Integer id) {
        taskMapper.delete(id);
    }

    @Override
    public void update(Task task) {
        taskMapper.update(task);
    }

    @Override
    public Task selectTask(Integer taskid) {
        Task task = taskMapper.selectTask(taskid);
        return task;
    }

    @Override
    public void setLevel(Integer level, Integer id) {
        taskMapper.setLevel(level, id);
    }
}
