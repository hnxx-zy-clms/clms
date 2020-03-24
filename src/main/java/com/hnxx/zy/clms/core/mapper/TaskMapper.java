package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 18:31
 * @version: 1.0
 * @desc:
 */
public interface TaskMapper {

    /**
     * 新建任务
     *
     * @param task
     */
    @Insert("insert into cl_task(created_id,task_title,task_content,is_enabled) values(#{task.createdId},#{task.taskTitle},#{task.taskContent},#{task.isEnabled})")
    void saveTask(@Param("task") Task task);

    /**
     * 新建回复
     *
     * @param task
     */
    @Insert("insert into cl_task_user(task_id,user_id,is_did,reply_content) values(#{task.taskId},#{task.userId},1,#{task.replyContent})")
    void saveReply(@Param("task") Task task);

    /**
     * 学生获取所有任务及完成情况
     *
     * @param id
     * @return
     */
    @Select("SELECT a.*,b.is_did,c.user_name FROM cl_task a LEFT JOIN cl_task_user b ON a.task_id=b.task_id and b.user_id=1  LEFT JOIN cl_user c ON a.created_id=c.user_id where a.is_enabled=1")
    List<Task> getAllListByUserId(Integer id);

    /**
     * 学生查看任务内容及回复内容
     *
     * @param taskid
     * @param userid
     * @return
     */
    @Select("SELECT did_time,reply_content FROM cl_task_user where task_id=#{taskid} AND user_id=#{userid}")
    Task getTaskReply(Integer taskid, Integer userid);
}
