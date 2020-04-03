package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Task;
import com.hnxx.zy.clms.core.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 18:44
 * @version: 1.0
 * @desc:
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * 新建任务
     *
     * @param task
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Task task) {
        taskService.saveTask(task);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 新建回复
     *
     * @param task
     * @return
     */
    @PostMapping("/saveReply")
    public Result saveReply(@RequestBody Task task) {
        taskService.saveReply(task);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据学生id分页获取任务及是否完成
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserTask/{id}")
    public Result<Page> getUserTask(@RequestBody Page page, @PathVariable("id") Integer id) {
        page.setIndex(page.getIndex());
        page = taskService.getByPage(page, id);
        return new Result<>(page);
    }

    /**
     * 学生获取选中的任务的内容及回复内容
     * 教师查看学生的任务回复内容
     *
     * @param taskid
     * @param userid
     * @return
     */
    @GetMapping("/getTaskReply/{taskid}/{userid}")
    public Result getTaskReply(@PathVariable("taskid") Integer taskid, @PathVariable("userid") Integer userid) {
        Task task = taskService.getTaskReply(taskid, userid);
        return new Result(task);
    }

    /**
     * 教师分页获取所有任务
     *
     * @return
     */
    @GetMapping("/getList")
    public Result<Page> getList(@RequestBody Page page) {
        page.setIndex(page.getIndex());
        page = taskService.getAllTaskByPage(page);
        return new Result(page);
    }

    /**
     * 教师分页查看某任务的完成详情
     *
     * @param taskid
     * @return
     */
    @GetMapping("/getTaskSituation/{taskid}")
    public Result getTaskSituation(@RequestBody Page page, @PathVariable("taskid") Integer taskid) {
        page.setIndex(page.getIndex());
        page = taskService.getTaskSituation(page, taskid);
        return new Result(page);
    }

    /**
     * 删除任务
     *
     * @param id
     * @return
     */
    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        taskService.deleteTask(id);
        return new Result("删除成功");
    }
}
