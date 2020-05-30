/**
 * @FileName: CommentController
 * @Author: code-fusheng
 * @Date: 2020/3/24 14:42
 * Description: 评论控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.core.entity.Comment;
import com.hnxx.zy.clms.core.service.CommentService;
import com.hnxx.zy.clms.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    /**
     * 保存添加评论
     * @return
     */
    @PostMapping("/save")
    public Result<Comment> save(@RequestBody Comment comment){
        comment.setCommentUser(userService.getUserName());
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
     * 通过id获取文章的评论列表
     * @param id
     * @return
     */
    @GetMapping("/getList/{id}")
    public Result<List<Comment>> getList(@PathVariable("id") Integer id){
        List<Comment> commentList = commentService.getListById(id);
        return new Result<>(commentList);
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

    /**
     * 根据文章id分页查询 一级评论列表
     * @param page
     * @return
     */
    @PostMapping("/getCommentList")
    public Result<Page<Comment>> getCommentList(@RequestBody Page<Comment> page){
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        // 下划线的 排序列
        page.setSortColumn(newSortColumn);
        // 判断排序列不为空
        if(StringUtils.isNotBlank(sortColumn)){
            // 评论的评论量 评论的点赞量 评论时间
            String[] sortColumns = {"comment_count", "comment_good", "comment_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"参数错误！");
            }
        }
        page = commentService.getCommentList(page);
        return new Result<>(page);
    }

    /**
     * 根据一级评论id分页查询 二级评论列表
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<Comment>> getByPage(@RequestBody Page<Comment> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        // 下划线的 排序列
        page.setSortColumn(newSortColumn);
        // 判断排序列不为空
        if(StringUtils.isNotBlank(sortColumn)){
            String[] sortColumns = {"pid", "comment_content", "comment_count", "comment_good", "comment_time"};
            // Arrays.asList() 方法使用
            // 1. 该方法是将数组转换成list。 Json 数据格式中的 排序列为数组形式，此处需要转换成 List数据形式
            // 2. 该方法不适用于剧本数据类型（byte,short,int,long,float,double,boolean）
            // 3. 不支持add和remove方法
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"参数错误！");
            }
        }
        page = commentService.getByPage(page);
        return new Result<>(page);
    }



}
