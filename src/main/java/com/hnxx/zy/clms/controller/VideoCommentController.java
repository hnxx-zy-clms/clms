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

    @PostMapping("/getVideoCommentById")
    public Result<Page<VideoComment>> getVideoCommentById(@RequestBody Page<VideoComment> page){
        List<VideoComment> videoCommentList=videoCommentService.getVideoCommentById(page);
        page.setList(videoCommentList);
        page.setTotalCount(videoCommentList.size());
        page.pagingDate();
        return new Result<>(page);
    }

    @GetMapping("/getVideoCommentParentById/{id}")
    public Result<List<VideoComment>> getVideoCommentParentById(@PathVariable Integer id){
        List<VideoComment> videoCommentList=videoCommentService.getVideoCommentParentById(id);
        return new Result<>(videoCommentList);
    }

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
