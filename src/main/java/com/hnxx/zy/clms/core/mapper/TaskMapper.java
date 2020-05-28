package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Task;
import com.hnxx.zy.clms.core.entity.TaskUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 18:31
 * @version: 1.0
 * @desc:
 */
@Mapper
@Repository
public interface TaskMapper {

    /**
     * 新建任务
     *
     * @param task
     */
    @Insert("<script>" +
            "insert into cl_task(created_id,task_content,task_title,is_enabled,file_name,file_url" +
            "        <if test=\"task.pushedTime!=null\">\n" +
            "             ,pushed_time\n" +
            "        </if>" +
            ") values (#{task.createdId},#{task.taskContent},#{task.taskTitle},#{task.isEnabled},#{task.fileName},#{task.fileUrl}" +
            "        <if test=\"task.pushedTime!=null\">\n" +
            "             ,date_format(#{task.pushedTime}, '%Y-%m-%d %H:%i:%s')\n" +
            "        </if>" +
            ")" +
            "</script>")
    void saveTask(@Param("task") Task task);

    /**
     * 新建回复
     *
     * @param taskUser
     */
    @Insert("insert into cl_task_user(task_id,user_id,is_did,reply_content,file_name,file_url) values(#{taskUser.taskId},#{taskUser.userId},1,#{taskUser.replyContent},#{taskUser.fileName},#{taskUser.fileUrl})")
    void saveReply(@Param("taskUser") TaskUser taskUser);

    /**
     * 学生分页获取所有任务及完成情况
     *
     * @param page
     * @param id
     * @return
     */
    @Select("<script>"+
            "SELECT a.task_id,a.task_title,a.task_content,a.pushed_time,c.user_name,b.id" +
            " FROM cl_task a LEFT JOIN cl_task_user b ON a.task_id=b.task_id and b.user_id=#{id}  LEFT JOIN cl_user c ON a.created_id=c.user_id where a.is_enabled=1 and a.is_deleted=0"+
            "        <if test=\"page.params.type==1\">\n" +
            "             and id IS NOT NULL" +
            "        </if>" +
            "        <if test=\"page.params.type==2\">\n" +
            "             and id IS NULL" +
            "        </if>" +
            " ORDER BY a.created_time desc LIMIT #{page.index}, #{page.pageSize}"+
            "</script>")
    @Results({
            @Result(property = "taskUser", column = "id",
            one = @One(select = "com.hnxx.zy.clms.core.mapper.TaskMapper.getTaskDes"))
    })
    List<Task> getByPage(@Param("page") Page page, Integer id);

    /**
     * @Description: 根据Id获取任务回复等级
     * @Param:
     * @return:
     * @Author: CHENLH
     * @Date: 2020-05-03 08:33:28
     */
    @Select("select IFNULL(level,0) AS level from cl_task_user where id = #{id}")
    TaskUser getTaskDes(Integer id);
    /**
     * 查询总数
     *
     * @return
     */
    @Select("<script>" +
            "        select count(*) from cl_task a left JOIN cl_user b ON a.created_id=b.user_id  WHERE 1>0" +
            "        <if test=\"page.params.Title!=null and page.params.Title!=''\">\n" +
            "           and task_title like CONCAT('%', #{page.params.Title}, '%')\n" +
            "        </if>" +
            "        <if test=\"page.params.createdName!=null and page.params.createdName!=''\">\n" +
            "             and b.user_name like CONCAT('%', #{page.params.createdName}, '%')\n" +
            "        </if>" +
            "        <if test=\"page.params.createdTime!=null and page.params.createdTime!=''\">\n" +
            "             and a.created_time like CONCAT('%', #{page.params.createdTime}, '%')\n" +
            "        </if>" +
            "        <if test=\'page.params.role==\"student\" \'>\n" +
            "           and a.is_deleted =0 and a.is_enabled=1" +
            "        </if>" +
            "        </script>")
    int getCountByPage(@Param("page") Page page);

    /**
     * 学生查看任务内容及回复内容
     * 教师查看学生完成内容
     *
     * @param taskid
     * @param userid
     * @return
     */
    @Select("SELECT a.*,b.user_name FROM cl_task_user a left join cl_user b on a.user_id = b.user_id where task_id=#{taskid} AND b.user_id=#{userid}")
    TaskUser getTaskReply(Integer taskid, Integer userid);

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
    @Select("<script>" +
            "select a.user_id,a.user_name ,IFNULL(b.is_did,0) as is_did,b.did_time,b.id FROM cl_user a LEFT JOIN cl_task_user b ON a.user_id=b.user_id and b.task_id=#{id} where a.user_position_id in (1,2)  limit #{page.index}, #{page.pageSize}" +
            "</script>")
    @Results({
            @Result(property = "Level",
                    column = "id",
                    one = @One(select = "com.hnxx.zy.clms.core.mapper.TaskMapper.getTaskLevel"))
    })
    List<TaskUser> getTaskSituation(@Param("page") Page page, @Param("id") Integer id);

    @Select("select level from cl_task_user where id = #{id}")
    int getTaskLevel(Integer id);


    /**
     * 删除任务
     *
     * @param id
     */
    @Update("update cl_task set is_deleted=1 where task_id=#{id}")
    void deleteTask(Integer id);

    /**
     * 分页获取任务列表
     *
     * @param page
     * @return
     */
    @Select("<script>" +
            "        SELECT a.*,b.user_name from cl_task a left JOIN cl_user b ON a.created_id=b.user_id where 1 > 0" +
            "        <if test=\"page.params.Title!=null and page.params.Title!=''\">\n" +
            "             and a.task_title like CONCAT('%', #{page.params.Title}, '%')\n" +
            "        </if>" +
            "        <if test=\"page.params.createdName!=null and page.params.createdName!=''\">\n" +
            "             and b.user_name like CONCAT('%', #{page.params.createdName}, '%')\n" +
            "        </if>" +
            "        <if test=\"page.params.createdTime!=null and page.params.createdTime!=''\">\n" +
            "             and a.created_time like CONCAT('%', #{page.params.createdTime}, '%')\n" +
            "        </if>" +
            "        <if test=\'page.params.statu==\"saved\"\'>\n" +
            "             and a.is_enabled=0 and a.is_deleted=0\n" +
            "        </if>" +
            "        <if test=\'page.params.statu==\"pushed\"\'>\n" +
            "             and a.is_enabled=1 and a.is_deleted=0\n" +
            "        </if>" +
            "        <if test=\'page.params.statu==\"deleted\"\'>\n" +
            "             and a.is_deleted=1\n" +
            "        </if>" +
            "        <if test=\"page.sortColumn!=null and page.sortColumn!=''\">\n" +
            "            order by ${page.sortColumn} ${page.sortMethod}\n" +
            "        </if>\n" +
            "           LIMIT #{page.index}, #{page.pageSize}" +
            "</script>")
    @Results({
            @Result(property = "numDid",
                    column = "task_id",
                    one = @One(select = "com.hnxx.zy.clms.core.mapper.TaskMapper.selectnum")),
            @Result(property = "taskId",
                    column = "task_id")
    })
    List<Task> getByPageAdmin(@Param("page") Page page);


    /**
     * 获取已完成人数
     *
     * @param id
     * @return
     */
    @Select("select count(*) from cl_task_user where task_id=#{id}")
    int selectnum(@Param("id") Integer id);

    /**
     * 批量删除
     *
     * @param params
     */
    @Update("<script>" +
            "update cl_task set is_deleted=1 WHERE cl_task.task_id in " +
            "<foreach collection='params' item='param' open='(' separator=',' close=')'>" +
            "   #{param}" +
            "</foreach>" +
            "</script>")
    void deleteTasks(@Param("params") Integer[] params);

    /**
     * @Description: 批量删除任务回复
     * @Param: [params]
     * @return: void
     * @Author: CHENLH
     * @Date: 2020-05-28 09:04:19
     */
    @Update("<script>"+
            "delete from cl_task_user WHERE task_id in "+
            "<foreach collection='params' item='param' open='(' separator=',' close=')'>"+
            "   #{param}"+
            "</foreach>"+
            "</script>")
    void deleteTaskUsers(@Param("params") Integer[] params);

    /**
     * 物理删除
     *
     * @param id
     */
    @Delete("DELETE FROM cl_task WHERE task_id = #{id}")
    void delete(@Param("id") Integer id);

    /**
     * 物理删除回复
     *
     * @param id
     */
    @Delete("DELETE FROM cl_task_user WHERE task_id = #{id}")
    void deleteUserReply(@Param("id") Integer id);

    /**
     * 将已保存状态改为发布状态
     *
     * @param id
     */
    @Update("update cl_task set pushed_time=date_format(#{time}, '%Y-%m-%d %H:%i:%s'),is_Enabled =1 ,is_deleted=0 where task_id = #{id}")
    void savedTopushed(@Param("id") Integer id, @Param("time") Date date);

    /**
     * 更新任务
     *
     * @param task
     */
    @Update("update cl_task set created_time=#{task.createdTime},pushed_time=#{task.pushedTime},task_content=#{task.taskContent},task_title=#{task.taskTitle},is_enabled=#{task.isEnabled},is_deleted=#{task.isDeleted},file_name=#{task.fileName},file_url=#{task.fileUrl} where task_id = #{task.taskId}")
    void update(@Param("task") Task task);

    /**
     * 获取单个任务
     * @param taskid
     * @return
     */
    @Select("select * from cl_task where task_id = #{taskid}")
    Task selectTask(@Param("taskid") Integer taskid);

    /**
     * 设置学生任务回复等级
     * @param level
     * @param id
     */
    @Update("update cl_task_user set level = #{level} where id = #{id}")
    void setLevel(@Param("level") Integer level ,@Param("id") Integer id);




}
