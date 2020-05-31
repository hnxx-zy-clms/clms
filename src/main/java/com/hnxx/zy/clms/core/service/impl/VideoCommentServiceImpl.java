package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.VideoComment;
import com.hnxx.zy.clms.core.mapper.VideoCommentMapper;
import com.hnxx.zy.clms.core.service.VideoCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: clms
 * @description: VideoCommentService实现类
 * @author: nile
 * @create: 2020-05-31 14:30
 **/
@Service
public class VideoCommentServiceImpl implements VideoCommentService {

    @Resource
    private VideoCommentMapper videoCommentMapper;

    @Override
    public List<VideoComment> getVideoCommentById(Page<VideoComment> page) {
        int i = 0;
        List<VideoComment> videoCommentList =videoCommentMapper.getVideoCommentById(page);
        for (VideoComment videoComment : videoCommentList){
            List<VideoComment> list=videoCommentMapper.getVideoCommentParentById(videoComment.getVideoCommentId());
            i = i+list.size()+1;
            videoComment.setVideoCommentList(list);
        }
        page.params.put("videoCommentParentSum",i);
        return videoCommentList;
    }

}