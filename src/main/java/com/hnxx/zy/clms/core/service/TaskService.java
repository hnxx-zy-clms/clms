package com.hnxx.zy.clms.core.service;

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
     * 获取所有任务及完成情况
     *
     * @param id
     * @return
     */
    List<Task> getAllListByUserId(Integer id);

    /**
     * 获得已完成任务的详细内容及回复内容
     *
     * @param taskid
     * @param userid
     * @return
     */
    Task getTaskReply(Integer taskid, Integer userid);

}
