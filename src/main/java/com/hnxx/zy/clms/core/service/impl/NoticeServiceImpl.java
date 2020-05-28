package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.Notice;
import com.hnxx.zy.clms.core.mapper.NoticeMapper;
import com.hnxx.zy.clms.core.service.NoticeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/18 15:15
 * @version: 1.0
 * @desc:
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public void setChange(Integer noticeId,Integer userId) {
        noticeMapper.setChange(noticeId,userId);
    }

    @Override
    public void save(Notice notice) {
        noticeMapper.save(notice);
    }

    @Override
    public void delNotice(Integer id) {
        noticeMapper.delNotice(id);
        noticeMapper.deleteUserRead(id);
    }

    @Override
    public void delete(Integer id) {
        noticeMapper.delete(id);
    }

    @Override
    public Page<Notice> getByPage(Page<Notice> page, Integer id) {
        List<Notice> notices = noticeMapper.getByPage(page, id);
        page.setList(notices);

        int totalCount = noticeMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public Page<Notice> getByPageAdmin(Page<Notice> page) {
        List<Notice> notices = noticeMapper.getByPageAdmin(page);
        page.setList(notices);

        int totalCount = noticeMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public void deleteNotices(Integer[] params) {
        noticeMapper.deleteNotices(params);
        noticeMapper.deleteNoticesUser(params);
    }

    @Override
    public void savedTopushed(Integer id, Date date) {
        noticeMapper.savedTopushed(id,date);
    }

    @Override
    public void update(Notice notice){
        noticeMapper.update(notice);
    }


}
