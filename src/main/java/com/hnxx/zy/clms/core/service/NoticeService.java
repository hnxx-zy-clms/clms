package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Notice;
import org.aspectj.weaver.ast.Not;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/18 15:14
 * @version: 1.0
 * @desc:
 */
public interface NoticeService {

    /**
     * 获取所有通知
     *
     * @param id
     * @return
     */
    List<Notice> getAllNotice(Integer id);

    /**
     * 设置已读
     *
     * @param notice
     */
    void setChange(Notice notice);

    /**
     * 新建通知
     *
     * @param notice
     */
    void save(Notice notice);

    /**
     * 删除通知
     *
     * @param id
     */
    void delNotice(Integer id);

}
