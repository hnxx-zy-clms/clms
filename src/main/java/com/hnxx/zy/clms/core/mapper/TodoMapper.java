package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Todo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 12:44
 * @version: 1.0
 * @desc:
 */
@Mapper
@Repository
public interface TodoMapper {

    /**
     * 插入用户今日代办-
     *
     * @param todo
     */
    @Insert("insert into cl_todo(user_id,com_content,is_did) values(#{todo.userId},#{todo.comContent},0) ")
    void saveTodo(@Param("todo") Todo todo);

    /**
     * 设置代办为已完成
     *
     * @param id
     */
    @Update("update cl_todo set is_did=1 where com_id=#{id}")
    void setDid(Integer id);

    /**
     * 删除代办
     *
     * @param id
     */
    @Delete("delete from cl_todo where com_id=#{id}")
    void deleteTodo(Integer id);

    /**
     * 根据日期和用户id查询代办
     *
     * @param id
     * @param time
     * @return
     */
    @Select("select * from cl_todo where user_id=#{id} and DATE_FORMAT(created_time,'%Y-%m-%d')=#{time} ORDER BY created_time desc")
    List<Todo> getTodoByIdAndTime(Integer id, String time);

}

