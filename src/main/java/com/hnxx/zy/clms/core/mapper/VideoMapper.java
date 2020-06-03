package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 根据视频ID查询
     * * @param id
     * * * @param type
     * @return
     */
    @Select("SELECT\n" +
            "x.codename AS category,\n" +
            "a.created_time,\n" +
            "a.title,\n" +
            "a.video_good ,\n" +
            "a.video_id,\n" +
            "a.cover_url,\n" +
            "a.play_volume,\n" +
            "c.user_name as url\n" +
            "FROM\n" +
            "cl_video a\n" +
            "LEFT JOIN cl_user_video b ON a.video_id = b.video_id\n" +
            "LEFT JOIN cl_user c ON c.user_id = b.user_id\n" +
            "LEFT JOIN cl_dict x ON x.type = 'video' \n" +
            "AND x.CODE = a.category \n" +
            "WHERE\n" +
            "x.codename= #{id}\n" +
            "order by a.play_volume desc\n" +
            "limit 0, #{type}")
    List<Video> recommendVideo(String id,Integer type);

}
