package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.VideoComment;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: clms
 * @description: VideoComment服务
 * @author: nile
 * @create: 2020-05-31 14:29
 **/
public interface VideoCommentService {

    /**
     * 根据视频ID查询
     * @param page
     * @return
     */
    List<VideoComment> getVideoCommentById(Page<VideoComment> page);

    /**
     * 根据视频ID查询
     * @param id
     * @return
     */
    List<VideoComment>  getVideoCommentParentById(Integer id);

    /**
     * 添加评论
     * @param videoComment
     * @return
     */
    void setVideoComment(VideoComment videoComment);

}
