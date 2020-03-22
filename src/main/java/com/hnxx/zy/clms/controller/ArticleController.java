/**
 * @FileName: ArticleController
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:16
 * Description: 文章控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 保存【新增】
     * @param article
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Article article){
        articleService.save(article);
        return new Result<>("添加成功！");
    }



}
