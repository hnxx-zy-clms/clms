/**
 * @FileName: AnswerController
 * @Author: code-fusheng
 * @Date: 2020/4/11 22:31
 * Description: 答复控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.core.entity.Answer;
import com.hnxx.zy.clms.core.service.AnswerService;
import com.hnxx.zy.clms.core.service.QuestionService;
import com.hnxx.zy.clms.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    /**
     * 保存
     * @param answer
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Answer answer){
        answer.setAnswerAuthor(userService.getUserName());
        // 答复初始创建时 默认为 未采纳
        answer.setAnswerMark(StateEnum.NO_ADOPT_ANSWER.getCode());
        answerService.save(answer);
        return new Result<>("保存成功!");
    }

    /**
     * 根据id采纳答复
     * @param id
     * @return
     */
    @PutMapping("/isAdopt/{id}")
    @Transactional(rollbackFor = Exception.class)
    public Result<Object> isAdopt(@PathVariable Integer id){
        answerService.isAdopt(id);
        questionService.isSolve(answerService.getById(id).getQuestionId());
        return new Result<>("答复状态更新成功:已采纳!");
    }

    /**
     * 根据id未采纳答复
     * @param id
     * @return
     */
    @PutMapping("/noAdopt/{id}")
    public Result<Object> noAdopt(@PathVariable Integer id){
        answerService.noAdopt(id);
        return new Result<>("答复状态更新成功:未采纳!");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable("id") Integer id){
        answerService.deleteById(id);
        return new Result<>("删除成功!");
    }

    /**
     * 修改
     * @param answer
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody Answer answer){
        answerService.update(answer);
        return new Result<>("修改成功!");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<Answer> get(@PathVariable("id") Integer id){
        Answer answer = answerService.getById(id);
        return new Result<>(answer);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<Answer>> getByPage(@RequestBody Page<Answer> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        // 下划线的 排序列
        page.setSortColumn(newSortColumn);
        // 判断排序列不为空
        if(StringUtils.isNotBlank(sortColumn)){
            // 提问时间，修改时间
            String[] sortColumns = {"answer_good","answer_time", "update_time"};
            // Arrays.asList() 方法使用
            // 1. 该方法是将数组转换成list。 Json 数据格式中的 排序列为数组形式，此处需要转换成 List数据形式
            // 2. 该方法不适用于剧本数据类型（byte,short,int,long,float,double,boolean）
            // 3. 不支持add和remove方法
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"参数错误！");
            }
        }
        page = answerService.getByPage(page);
        return new Result<>(page);
    }


}
