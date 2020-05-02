package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Notice;
import com.hnxx.zy.clms.core.mapper.NoticeMapper;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.core.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    private UserMapper userMapper;

    /**
     * 设置已读
     *
     * @param noticeId,userId
     * @return
     */
    @PostMapping("/changeRead/{noticeId}/{userId}")
    public Result changeRead(@PathVariable("noticeId") Integer noticeId,@PathVariable("userId") Integer userId) {
        noticeService.setChange(noticeId,userId);
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
        if (notice.isEnabled()==false){
            notice.setPushedTime(null);
        }else {
            notice.setPushedTime(new Date());
        }
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
     * 学生分页获取通知
     *
     * @param page
     * @param id
     * @return
     */
    @PostMapping("getByPage/{id}")
    public Result<Page> getByPage(@RequestBody Page page, @PathVariable("id") Integer id) {
        page.setIndex(page.getIndex());
        page = noticeService.getByPage(page, id);
        return new Result<>(page);

    }

    /**
     * 教师分页获取
     * @param page
     * @return
     */
    @PostMapping("getByPageAdmin")
    public Result<Page> getByPageAdmin(@RequestBody Page page) {
        page.setIndex(page.getIndex());
        page = noticeService.getByPageAdmin(page);
        return new Result<>(page);

    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteByIds")
    public Result<Page> deletes(Integer[] ids){
        noticeService.deleteNotices(ids);
        return new Result<>(ResultEnum.SUCCESS);
    }

    /**
     * 获取总人数
     * @return
     */
    @GetMapping("getUserNum")
    public Result getUserNum(){
        int i = userMapper.selectUserNum();
        return new Result<>(i);
    }

    /**
     * 获取用户Id
     * @param name
     * @return
     */
    @GetMapping("getUserId/{name}")
    public Result getUserId(@PathVariable("name") String name){
        int i = userMapper.selectUserId(name);
        return new Result(i);
    }

    /**
     * 保存转为发布
     * @param id
     * @return
     */
    @PutMapping("saveTopush/{id}/{time}")
    public Result savedTopushed(@PathVariable("id") Integer id, @PathVariable("time")Date date){
        noticeService.savedTopushed(id,date);
        return new Result<>(ResultEnum.SUCCESS);
    }

    /**
     *删除转发布
     * @param notice
     * @return
     */
    @PostMapping("deleteTopush")
    public Result deleteTopush(@RequestBody Notice notice){
        notice.setPushedTime(new Date());
        noticeService.delete(notice.getNoticeId());
        notice.setEnabled(true);
        noticeService.save(notice);
        return new Result<>(ResultEnum.SUCCESS);
    }

    /**
     * 更新通知
     * @param notice
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody Notice notice){
        if (notice.isEnabled()==false){
            notice.setPushedTime(null);
        }else {
            notice.setPushedTime(new Date());
        }
        noticeService.update(notice);
        return new Result(ResultEnum.SUCCESS);
    }


}
