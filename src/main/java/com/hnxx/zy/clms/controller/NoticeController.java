package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Notice;
import com.hnxx.zy.clms.core.service.NoticeService;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/17 22:53
 * @version: 1.0
 * @desc:
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private static Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    /**
     * 获取所有通知
     *
     * @param id
     * @return
     */
    @GetMapping("/noticelist/{id}")
    public Result getAllNotice(@PathVariable("id") Integer id) {
        List<Notice> notice = noticeService.getAllNotice(id);
        return new Result<>(notice);
    }

    /**
     * 设置已读
     *
     * @param notice
     * @return
     */
    @PostMapping("/changeread")
    public Result changeRead(@RequestBody Notice notice) {
        notice.setIfRead(true);
        noticeService.setChange(notice);
        return new Result<>(ResultEnum.SUCCESS);
    }

    /**
     * 新建通知
     *
     * @param notice
     * @return
     */
    @PostMapping("/savenotice")
    public Result saveNotice(@RequestBody Notice notice) {
        noticeService.save(notice);
        return new Result<>(ResultEnum.SUCCESS);
    }

    /**
     * 删除通知
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delnotice/{id}")
    public Result delNotice(@PathVariable("id") Integer id) {
        noticeService.delNotice(id);
        return new Result<>(ResultEnum.SUCCESS);
    }


}
