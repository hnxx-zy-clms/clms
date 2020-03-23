/**
 * @FileName: ArticleController
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:16
 * Description: 文章控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    /**
     * 根据id查询 [后台使用]
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<Object> get(@PathVariable("id") Integer id){
        Article article = articleService.getById(id);
        return new Result<>(article);
    }

    /**
     * 更新成功
     * @param article
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody Article article){
        articleService.update(article);
        return new Result<>("更新成功!");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable("id") Integer id){
        articleService.deleteById(id);
        return new Result<>("删除成功!");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<Article>> getByPage(@RequestBody Page<Article> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            String[] sortColumns = {"article_good", "article_read", "article_collection", "article_type", "article_comment", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "参数错误！");
            }
        }
        page = articleService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 启用
     * @param id
     * @return
     */
    @PutMapping("/enable/{id}")
    public Result<Object> enable(@PathVariable("id") Integer id) {
        articleService.enableById(id);
        return new Result<>("启用成功");
    }

    /**
     * 弃用
     * @param id
     * @return
     */
    @PutMapping("/disable/{id}")
    public Result<Object> disable(@PathVariable("id") Integer id) {
        articleService.disableById(id);
        return new Result<>("弃用成功");
    }


}
