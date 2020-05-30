package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @program: clms
 * @description: 视频mapper接口
 * @author: nile
 * @create: 2020-05-30 13:15
 **/
@Mapper
@Repository
public interface VideoMapper {

    /**
     * 根据视频ID查询
     * @param id
     * @return
     */
    @Select("select x.codename as category,a.*,b.user_id from cl_video a left join cl_user_video b on a.video_id = b.video_id \n" +
            "left join cl_dict x on x.type='video' and x.code = a.category \n" +
            " where a.video_id = #{id}")
    Video getVideoById(Integer id);

    /**
     * 根据视频ID查询
     * * @param id
     * @return
     */
    @Update("update cl_video set play_volume = play_volume+1 where video_id = #{id}")
    void addPlay(Integer id);


}
