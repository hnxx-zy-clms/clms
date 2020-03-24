package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
     * 学生分页获取所有任务及完成情况
     *
     * @param page
     * @param id
     * @return
     */
    @Select("SELECT a.*,IFNULL(b.is_did,0) as is_did,c.user_name FROM cl_task a LEFT JOIN cl_task_user b ON a.task_id=b.task_id and b.user_id=#{id}  LEFT JOIN cl_user c ON a.created_id=c.user_id where a.is_enabled=1 and a.is_deleted=0 ORDER BY a.created_time desc LIMIT #{page.index}, #{page.pageSize}")
    List<Task> getByPage(@Param("page") Page page, Integer id);

    /**
     * 查询总数
     *
     * @return
     */
    @Select("select count(*) from cl_task WHERE is_deleted =0 and is_enabled=1")
    int getCountByPage();

    /**
     * 学生查看任务内容及回复内容
     * 教师查看学生完成内容
     *
     * @param taskid
     * @param userid
     * @return
     */
    @Select("SELECT did_time,reply_content FROM cl_task_user where task_id=#{taskid} AND user_id=#{userid}")
    Task getTaskReply(Integer taskid, Integer userid);

    /**
     * 教师分页获取任务列表
     *
     * @param page
     * @return
     */
    @Select("select * from cl_task ORDER BY created_time desc limit #{page.index}, #{page.pageSize}")
    List<Task> getAllTaskByPage(@Param("page") Page page);

    /**
     * 分页获取该任务的完成情况
     *
     * @param page
     * @param id
     * @return
     */
    @Select("select a.user_id,a.user_name ,IFNULL(b.is_did,0) as is_did FROM cl_user a LEFT JOIN cl_task_user b ON a.user_id=b.user_id and b.task_id=#{id} where a.user_right=0 limit #{page.index}, #{page.pageSize}")
    List<Task> getTaskSituation(@Param("page") Page page, Integer id);

    /**
     * 获取回复总数
     *
     * @return
     */
    @Select("select count(*) from cl_user where user_right=0")
    int getSituationCountByPage();

    /**
     * 删除任务
     *
     * @param id
     */
    @Update("update cl_task set id_deleted=1 where task_id=#{id}")
    void deleteTask(Integer id);


}
