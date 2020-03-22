package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
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
    @PostMapping("/savetask")
    public Result saveTask(@RequestBody Task task) {
        taskService.saveTask(task);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 新建回复
     *
     * @param task
     * @return
     */
    @PostMapping("/savereply")
    public Result saveReply(@RequestBody Task task) {
        taskService.saveReply(task);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 学生获取任务及是否完成
     *
     * @param id
     * @return
     */
    @GetMapping("/getusertask/{id}")
    public Result getUserTask(@PathVariable("id") Integer id) {
        List<Task> tasks = taskService.getAllListByUserId(id);
        return new Result(tasks);
    }

    /**
     * 获取选中的任务的内容及回复内容
     *
     * @param taskid
     * @param userid
     * @return
     */
    @GetMapping("/gettaskreply/{taskid}/{userid}")
    public Result getTaskReply(@PathVariable("taskid") Integer taskid, @PathVariable("userid") Integer userid) {
        Task task = taskService.getTaskReply(taskid, userid);
        return new Result(task);
    }
}
