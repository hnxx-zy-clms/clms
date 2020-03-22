/**
 * @FileName: ArticleTypeController
 * @Author: code-fusheng
 * @Date: 2020/3/22 13:08
 * Description: 文章类型控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.ArticleType;
import com.hnxx.zy.clms.core.service.ArticleTypeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articleType")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    /**
     * 保存，添加 文章类型
     * @param articleType
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody ArticleType articleType){
        articleTypeService.save(articleType);
        return new Result<>("添加成功！");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<ArticleType> getById(@PathVariable("id") Integer id){
        ArticleType articleType = articleTypeService.getById(id);
        return new Result<>(articleType);
    }

    /**
     * 前台查询所有 已启用 未删除
     * @return
     */
    @GetMapping("/getList")
    public Result<List<ArticleType>> getList(){
        List<ArticleType> articleTypeList = articleTypeService.getList();
        return new Result<>(articleTypeList);
    }

    /**
     * 后台查询所有 已启用/未启用 未删除
     * @return
     */
    @GetMapping("/getAll")
    public Result<List<ArticleType>> getAll(){
        List<ArticleType> articleTypeList = articleTypeService.getAll();
        return new Result<>(articleTypeList);
    }

    /**
     * 修改
     * @param articleType
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody ArticleType articleType){
        articleTypeService.update(articleType);
        return new Result<>("修改成功!");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable("id") Integer id){
        articleTypeService.deleteById(id);
        return new Result<>("删除成功!");
    }

    /**
     * 根据id启用
     * @param id
     * @return
     */
    @PutMapping("/enable/{id}")
    public Result<Object> enable(@PathVariable("id") Integer id){
        articleTypeService.enabledById(id);
        return new Result<>("已启用!");
    }

    /**
     * 根据id弃用
     * @param id
     * @return
     */
    @PutMapping("/disable/{id}")
    public Result<Object> disable(@PathVariable("id") Integer id){
        articleTypeService.disabledById(id);
        return new Result<>("已弃用!");
    }



}
