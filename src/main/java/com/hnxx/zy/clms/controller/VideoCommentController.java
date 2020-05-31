package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.VideoComment;
import com.hnxx.zy.clms.core.service.VideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/getVideoCommentById")
    public Result<Page<VideoComment>> getVideoCommentById(@RequestBody Page<VideoComment> page){
        List<VideoComment> videoCommentList=videoCommentService.getVideoCommentById(page);
        page.setList(videoCommentList);
        page.setTotalCount(videoCommentList.size());
        page.pagingDate();
        return new Result<>(page);
    }

}
