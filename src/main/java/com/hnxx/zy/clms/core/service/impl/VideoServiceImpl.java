package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.entity.Video;
import com.hnxx.zy.clms.core.mapper.VideoMapper;
import com.hnxx.zy.clms.core.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: clms
 * @description: 视频服务实现类
 * @author: nile
 * @create: 2020-05-30 13:32
 **/
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public Video getVideoById(Integer id) {
        videoMapper.addPlay(id);
        return videoMapper.getVideoById(id);
    }

    @Override
    public List<Video> recommendVideo(String  id, Integer type) {
        return videoMapper.recommendVideo(id,type);
    }
}
