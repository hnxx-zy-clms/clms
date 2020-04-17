/**
 * @FileName: QuestionController
 * @Author: code-fusheng
 * @Date: 2020/4/11 22:30
 * Description: 问题控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.core.entity.Question;
import com.hnxx.zy.clms.core.entity.Xxx;
import com.hnxx.zy.clms.core.service.QuestionService;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    /**
     * 保存
     * @param question
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Question question){
        question.setQuestionAuthor(userService.getUserName());
        // 提出问题时, 设置初始化问题状态值 未解决
        question.setQuestionMark(StateEnum.NO_SOLVE_QUESTION.getCode());
        questionService.save(question);
        return new Result<>("保存成功!");
    }

    /**
     * 已解答
     * @param id
     * @return
     */
    @PutMapping("/isSolve/{id}")
    public Result<Object> isSolve(@PathVariable Integer id){
        questionService.isSolve(id);
        return new Result<>("问题状态更新成功:已解答!");
    }

    /**
     * 未解答
     * @param id
     * @return
     */
    @PutMapping("/noSolve/{id}")
    public Result<Object> noSolve(@PathVariable Integer id){
        questionService.noSolve(id);
        return new Result<>("问题状态更新成功:未解答!");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable("id") Integer id){
        questionService.deleteById(id);
        return new Result<>("删除成功!");
    }

    /**
     * 修改
     * @param question
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody Question question){
        questionService.update(question);
        return new Result<>("修改成功!");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<Question> get(@PathVariable("id") Integer id){
        Question question = questionService.getById(id);
        return new Result<>(question);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<Question>> getByPage(@RequestBody Page<Question> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        // 下划线的 排序列
        page.setSortColumn(newSortColumn);
        // 判断排序列不为空
        if(StringUtils.isNotBlank(sortColumn)){
            // 提问时间，修改时间 问题点赞量,问题答复量
            String[] sortColumns = {"question_good", "answer_count", "question_time", "update_time"};
            // Arrays.asList() 方法使用
            // 1. 该方法是将数组转换成list。 Json 数据格式中的 排序列为数组形式，此处需要转换成 List数据形式
            // 2. 该方法不适用于剧本数据类型（byte,short,int,long,float,double,boolean）
            // 3. 不支持add和remove方法
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"参数错误！");
            }
        }
        page = questionService.getByPage(page);
        return new Result<>(page);
    }

}
