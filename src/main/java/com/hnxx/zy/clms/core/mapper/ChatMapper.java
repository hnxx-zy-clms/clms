package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author 黑鲨
 * @date 2020/3/25 17:52
 */
@Mapper
@Repository
public interface ChatMapper {

   @Insert("  INSERT INTO cl_chat(chat_user, chat_content,chat_type,created_time) " +
           "   VALUES (#{sender},#{content},#{type},#{createdTime})  ")
    void  chatSave(ChatMessage chatMessage);

   @Select( " SELECT c.chat_user sender ,c.chat_content content, c.chat_type type,c.created_time  createdTime   " +
           " FROM cl_chat c " +
           " where c.created_time BETWEEN #{startTime} and #{endTime} " +
           " ORDER BY  c.created_time asc  " )
   List<ChatMessage>   allChat(@Param("startTime") Date startTime, @Param("endTime")Date endTime);


}
