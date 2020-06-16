/**
 * @FileName: MessageMapper
 * @Author: fusheng
 * @Date: 2020/3/17 13:49
 * Description: MessageMapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 25610
 */

@Mapper
@Repository
public interface MessageMapper {

    /**
     * 保存
     * @param message
     */
    @Options(useGeneratedKeys = true, keyProperty = "messageId", keyColumn = "message_id")
    @Insert("insert into cl_message(message_content, message_desc ,send_user, receive_user, message_type, message_state) " +
            "values (#{messageContent}, #{messageDesc} ,#{sendUser}, #{receiveUser}, #{messageType}, #{messageState})")
    void save(Message message);

    /**
     * 根据id删除(物理删除)
     * @param id
     */
    @Delete("delete cl_message where message_id = #{id}")
    void deleteById(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    @Update("<script>" +
            "        update cl_message\n" +
            "        set is_deleted = 1" +
            "        where message_id in\n" +
            "        <foreach collection=\"list\" separator=\",\" item=\"id\" open=\"(\" close=\")\">\n" +
            "            #{id}\n" +
            "        </foreach>" +
            "</script>")
    void deleteByIds(List<Integer> ids);

    /**
     * 修改
     * @param message
     */
    @Update("<script>" +
            "        update cl_message set\n" +
            "        version = version + 1\n" +
            "        <if test=\"messageContent!=null and messageContent!=''\">\n" +
            "            ,message_content = #{messageContent}\n" +
            "        </if>\n" +
            "        where message_id = #{messageId}\n" +
            "        and version = #{version}" +
            "</script>")
    void update(Message message);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from cl_message where message_id = #{id} and is_deleted = 0")
    Message getById(Integer id);

    /**
     * 查询当前用户消息列表
     * @param user
     * @return
     */
    @Select("select * from cl_message where receive_user = #{user}")
    List<Message> getListByUser(String user);

    /**
     * 查询所有[后台查询]
     * @return
     */
    @Select("select * from cl_message")
    List<Message> getAll();


    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select * from cl_message\n" +
            "        where 1=1 \n" +
            "        <if test=\"params.sendUser!=null and params.sendUser!=''\">\n" +
            "            and send_user = #{params.sendUser}\n" +
            "        </if>\n" +
            "        <if test=\"params.receiveUser!=null and params.receiveUser!=''\">\n" +
            "            and receive_user = #{params.receiveUser}\n" +
            "        </if>\n" +
            "        <if test=\"params.messageState!=null and params.messageState!=''\">\n" +
            "            and message_state = #{params.messageState}\n" +
            "        </if>\n" +
            "        <if test=\"params.messageType!=null || params.messageType[0]!=null || params.messageType[1]!=null\">\n" +
            "            and message_type between #{params.messageType[0]} and #{params.messageType[1]}\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<Message> getByPage(Page<Message> page);

    /**
     * 统计总数
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select count(*) from cl_message\n" +
            "        where 1=1 \n" +
            "        <if test=\"params.sendUser!=null and params.sendUser!=''\">\n" +
            "            and send_user like CONCAT('%', #{params.sendUser}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.receiveUser!=null and params.receiveUser!=''\">\n" +
            "            and receive_user like CONCAT('%', #{params.receiveUser}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.messageState!=null and params.messageState!=''\">\n" +
            "            and message_state = #{params.messageState}\n" +
            "        </if>\n" +
            "        <if test=\"params.messageType!=null || params.messageType[0]!=null || params.messageType[1]!=null\">\n" +
            "            and message_type between #{params.messageType[0]} and #{params.messageType[1]}\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<Message> page);

    /**
     * 改变文章状态 (已读和未读)
     * @param message
     */
    @Update("update cl_message set message_state = #{messageState} where message_id = #{messageId}")
    void updateEnable(Message message);
}
