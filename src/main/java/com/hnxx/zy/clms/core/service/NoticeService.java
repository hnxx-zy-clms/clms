package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Not;

import java.util.Date;
import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/18 15:14
 * @version: 1.0
 * @desc:
 */
public interface NoticeService {

    /**
     * 设置为已读
     *
     * @param noticeId,userId
     */
    void setChange(Integer noticeId,Integer userId);

    /**
     * 新建通知
     *
     * @param notice
     */
    void save(Notice notice);

    /**
     * 逻辑删除通知
     *
     * @param id
     */
    void delNotice(Integer id);

    /**
     * 物理删除通知
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 学生分页获取通知
     *
     * @param page
     * @param id
     * @return
     */
    Page<Notice> getByPage(Page<Notice> page, Integer id);

    /**
     * 教师分页获取通知
     * @param page
     * @return
     */
    Page<Notice> getByPageAdmin(Page<Notice> page);

    /**
     * 批量删除通知
     * @param params
     */
    void deleteNotices(Integer [] params);

    /**
     * 将已保存状态改为发布
     * @param id
     */
    void savedTopushed(Integer id, Date date);

    /**
     * 更新通知
     * @param notice
     */
    void update(Notice notice);


    Integer getTeacherNoticeNum(Integer teacherId);
}
