/**
 * @FileName: CommentController
 * @Author: code-fusheng
 * @Date: 2020/3/24 14:42
 * Description: 评论控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Comment;
import com.hnxx.zy.clms.core.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 保存添加评论
     * @return
     */
    @PostMapping("/save")
    public Result<Comment> save(@RequestBody Comment comment){
        commentService.save(comment);
        return new Result<>("添加成功！");
    }

    /**
     * 根据id查询单条评论
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<Comment> get(@PathVariable("id") Integer id){
        Comment comment = commentService.getById(id);
        return new Result<>(comment);
    }

    /**
     * 查询所有 评论主体 (后台 层级结构)
     */
    @GetMapping("/getAll")
    public Result<List<Comment>> commentList(){
        List<Comment> commentList = commentService.getAll();
        return new Result<>(commentList);
    }

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable("id") Integer id){
        commentService.deleteById(id);
        return new Result<>("删除成功!");
    }

}
