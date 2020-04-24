package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Notice;
import com.hnxx.zy.clms.core.entity.Task;
import com.hnxx.zy.clms.core.entity.TaskUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
    TaskUser getTaskReply(Integer taskid, Integer userid);

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
     * @param taskuser
     * @param id
     * @return
     */
    Page<TaskUser> getTaskSituation(Page<TaskUser> taskuser, Integer id);

    /**
     * 逻辑删除任务
     *
     * @param id
     */
    void deleteTask(Integer id);

    /**
     * 将已保存状态改为发布
     * @param id
     */
    void savedTopushed(Integer id, Date date);

    /**
     * 物理删除通知
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 分页获取任务
     *
     * @param task
     * @param id
     * @return
     */
    Page<Task> getByPage(Page<Task> task, Integer id);

    /**
     * 教师分页获取任务
     * @param page
     */
    Page<Task> getByPageAdmin(Page<Task> page);

    /**
     * 批量删除通知
     * @param params
     */
    void deleteTasks(Integer [] params);

    /**
     * 更新任务
     * @param task
     */
    void update(Task task);

    /**
     * 获取单个任务
     * @param taskid
     * @return
     */
    Task selectTask(Integer taskid);

    /**
     * 设置学生任务回复等级
     * @param level
     * @param id
     */
    void setLevel(Integer level,Integer id);


}
