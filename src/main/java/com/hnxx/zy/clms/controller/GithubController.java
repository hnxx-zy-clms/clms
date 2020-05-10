package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.GithubCount;
import com.hnxx.zy.clms.core.mapper.GithubUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 南街北巷
 * @data 2020/5/10 1:34
 */
@RestController
@RequestMapping("/github")
public class GithubController {

    @Autowired
    private GithubUserMapper githubUserMapper;

    /**
     * 根据name查询所有github用户
     *
     * @param githubCount
     * @return
     */
    @PostMapping("/get/byName")
    public Result<Object> getGUByName(@RequestBody GithubCount githubCount) {
        List<GithubCount> githubList = githubUserMapper.getGUserByName(githubCount.getName());
        return new Result<>(githubList);
    }

    /**
     * 根据name查询所有github用户
     *
     * @param page
     * @return
     */
    @PostMapping("/get/byPage")
    public Result<Page> getGUByPage(@RequestBody Page page) {
        page.setIndex(page.getIndex());
        page=githubUserMapper.getByPage(page);
        return new Result<>(page);
    }
}
