package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @program: clms
 * @description: 视频实体类
 * @author: nile
 * @create: 2020-05-29 21:41
 **/
@Data
public class Video {

    /**
     * 作者Id
     */
    private Integer userId;

    /**
     * 视频Id
     */
    private Integer videoId;

    /**
     * 视频url
     */
    private String url;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频描述
     */
    private String description;

    /**
     * 播放量
     */
    private Integer playVolume;

    /**
     * 封面地址
     */
    private String coverUrl;

    /**
     * 点赞量
     */
    private Integer videoGood;

    /**
     * 收藏数
     */
    private Integer collect;

    /**
     * 类别
     */
    private String category;

    /**
     * 创建时间，返回时转成正常日期格式 年-月-日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

}
