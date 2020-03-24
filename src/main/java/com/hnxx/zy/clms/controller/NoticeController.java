package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Notice;
import com.hnxx.zy.clms.core.service.NoticeService;
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
     * 设置已读
     *
     * @param notice
     * @return
     */
    @PostMapping("/changeRead")
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
    @PostMapping("/save")
    public Result save(@RequestBody Notice notice) {
        noticeService.save(notice);
        return new Result<>(ResultEnum.SUCCESS);
    }

    /**
     * 删除通知
     *
     * @param id
     * @return
     */
    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        noticeService.delNotice(id);
        return new Result<>(ResultEnum.SUCCESS);
    }

    /**
     * 分页获取通知
     *
     * @param page
     * @param id
     * @return
     */
    @GetMapping("getByPage/{id}")
    public Result<Page> getByPage(@RequestBody Page page, @PathVariable("id") Integer id) {
        page.setIndex(page.getIndex());
        page = noticeService.getByPage(page, id);
        return new Result<>(page);

    }


}
