package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Notice;
import com.hnxx.zy.clms.core.entity.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 18:42
 * @version: 1.0
 * @desc:
 */
public interface TaskService {

    /**
     * 新建任务
     *
     * @param task
     */
    void saveTask(Task task);

    /**
     * 新建回复
     *
     * @param task
     */
    void saveReply(Task task);

    /**
     * 获得已完成任务的详细内容及回复内容
     *
     * @param taskid
     * @param userid
     * @return
     */
    Task getTaskReply(Integer taskid, Integer userid);

    /**
     * 教师分页获取任务列表
     *
     * @param task
     * @return
     */
    Page<Task> getAllTaskByPage(Page<Task> task);

    /**
     * 学生id获取该任务完成情况
     *
     * @param task
     * @param id
     * @return
     */
    Page<Task> getTaskSituation(Page<Task> task, Integer id);

    /**
     * 删除任务
     *
     * @param id
     */
    void deleteTask(Integer id);

    /**
     * 分页获取任务
     *
     * @param task
     * @param id
     * @return
     */
    Page<Task> getByPage(Page<Task> task, Integer id);

}
