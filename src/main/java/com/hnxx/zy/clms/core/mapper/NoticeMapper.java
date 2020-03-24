package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/18 14:14
 * @version: 1.0
 * @desc:
 */
public interface NoticeMapper {
    /**
     * 获取所有通知
     *
     * @param id
     * @return
     */
    @Select("SELECT a.*,b.if_read from cl_notice a left JOIN cl_notice_user b ON  b.user_id=#{id} and a.notice_id=b.notice_id")
    List<Notice> getAllNotice(Integer id);

    /**
     * 设置已读
     *
     * @param notice
     */
    @Insert("insert into cl_notice_user(notice_id,user_id,if_read) values (#{notice.noticeId},#{notice.userId},#{notice.ifRead})")
    void setChange(@Param("notice") Notice notice);

    /**
     * 新建通知
     *
     * @param notice
     */
    @Insert("insert into cl_notice(created_id,notice_content,notice_title,is_enabled) values (#{notice.createdId},#{notice.noticeContent},#{notice.noticeTitle},#{notice.isEnabled})")
    void save(@Param("notice") Notice notice);

    /**
     * 删除通知
     *
     * @param id
     */
    @Delete("DELETE cl_notice,cl_notice_user from cl_notice LEFT JOIN cl_notice_user ON cl_notice.notice_id=cl_notice_user.notice_id WHERE cl_notice.notice_id=#{id}")
    void delNotice(Integer id);
}