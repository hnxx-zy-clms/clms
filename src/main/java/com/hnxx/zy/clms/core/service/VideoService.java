package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Video;
import org.apache.ibatis.annotations.Select;

/**
 * @program: clms
 * @description: 视频service
 * @author: nile
 * @create: 2020-05-30 13:31
 **/
public interface VideoService {

    /**
     * 根据视频ID查询
     * @param id
     * @return
     */
    Video getVideoById(Integer id);
}
