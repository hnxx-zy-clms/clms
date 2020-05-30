package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Video;
import com.hnxx.zy.clms.core.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: clms
 * @description: 视频控制类
 * @author: nile
 * @create: 2020-05-30 13:38
 **/
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 根据视频ID查询
     * @param id
     * @return
     */
    @GetMapping("/getVideo/{id}")
    public Result<Video> getVideo(@PathVariable Integer id){
        Video video = videoService.getVideoById(id);
        return new Result<>(video);
    }
}
