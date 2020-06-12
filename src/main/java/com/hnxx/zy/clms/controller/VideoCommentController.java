package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.entity.VideoComment;
import com.hnxx.zy.clms.core.service.UserService;
import com.hnxx.zy.clms.core.service.VideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: clms
 * @description: VideoComment控制类
 * @author: nile
 * @create: 2020-05-31 14:34
 **/
@RestController
@RequestMapping("/videoComment")
public class VideoCommentController {

    @Autowired
    private VideoCommentService videoCommentService;

    @Autowired
    private UserService userService;

    /**
     * 根据视频ID查询
     * @param page
     * @return
     */
    @PostMapping("/getVideoCommentById")
    public Result<Page<VideoComment>> getVideoCommentById(@RequestBody Page<VideoComment> page){
        System.out.println(page.toString());
        List<VideoComment> videoCommentList = videoCommentService.getVideoCommentById(page);
        page.setList(videoCommentList);
        page.setTotalCount(videoCommentList.size());
        page.pagingDate();
        return new Result<>(page);
    }

    /**
     * 根据父级评论ID查询
     * @param id
     * @return
     */
    @GetMapping("/getVideoCommentParentById/{id}/{currentPage}")
    public Result<Page<VideoComment>> getVideoCommentParentById(@PathVariable Integer id,@PathVariable Integer currentPage){
        List<VideoComment> videoCommentList = videoCommentService.getVideoCommentParentById(id);
        Page<VideoComment> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(5);
        page.setTotalCount(videoCommentList.size());
        page.setList(videoCommentList);
        page.pagingDate();
        return new Result<>(page);
    }

    /**
     * 添加评论
     * @param videoComment
     * @return
     */
    @PostMapping("/setVideoComment")
    public Result<Object> setVideoComment(@RequestBody VideoComment videoComment) {
        if ("".equals(videoComment.getVideoCommentContent())){
            return new Result<>("评论不能为空！！！");
        }
        User user=userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        videoComment.setVideoCommentUserId(user.getUserId());
        if ("".equals(videoComment.getVideoCommentParentName())){
            videoComment.setVideoCommentParentName(null);
        }
        if (null == videoComment.getVideoCommentParent()){
            videoComment.setVideoCommentParent(0);
        }
        videoCommentService.setVideoComment(videoComment);
        return new Result<>();
    }
}
