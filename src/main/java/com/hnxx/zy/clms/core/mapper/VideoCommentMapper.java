package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.VideoComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: clms
 * @description: 视频评论Mapper
 * @author: nile
 * @create: 2020-05-31 13:45
 **/
@Mapper
public interface VideoCommentMapper {

    /**
     * 根据视频ID查询
     * @param page
     * @return
     */
    @Select("SELECT a.*,b.user_icon,b.user_name FROM cl_video_comment a left join cl_user b on b.user_id= a.video_comment_user_id \n" +
            " where video_id = #{params.id} and video_comment_parent = 0 and a.is_deleted = 0")
    List<VideoComment> getVideoCommentById(Page<VideoComment> page);

    /**
     * 根据视频ID查询
     * @param id
     * @return
     */
    @Select("SELECT a.*,b.user_icon,b.user_name FROM cl_video_comment a left join cl_user b on b.user_id= a.video_comment_user_id \n" +
            " where  video_comment_parent = #{id} and a.is_deleted = 0")
    List<VideoComment>  getVideoCommentParentById(Integer id);


}
