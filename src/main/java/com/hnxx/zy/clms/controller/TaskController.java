package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Task;
import com.hnxx.zy.clms.core.entity.TaskUser;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.core.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    private UserMapper userMapper;

    /**
     * 新建任务
     *
     * @param task
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Task task) {
        if (task.getIsEnabled() == false) {
            task.setPushedTime(null);
        } else {
            task.setPushedTime(new Date());
        }
        taskService.saveTask(task);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 新建回复
     *
     * @param taskUser
     * @return
     */
    @PostMapping("/saveReply")
    public Result saveReply(@RequestBody TaskUser taskUser) {
        taskService.saveReply(taskUser);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据学生id分页获取任务及是否完成
     *
     * @param id
     * @return
     */
    @PostMapping("/getUserTask/{id}")
    public Result<Page> getUserTask(@RequestBody Page page, @PathVariable("id") Integer id) {
        page.setIndex(page.getIndex());
        page = taskService.getByPage(page, id);
        return new Result<>(page);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("deleteByIds")
    public Result<Page> deletes(Integer[] ids) {
        taskService.deleteTasks(ids);
        return new Result<>(ResultEnum.SUCCESS);
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
        TaskUser taskUser = taskService.getTaskReply(taskid, userid);
        return new Result(taskUser);
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
    @PostMapping("/getTaskSituation/{taskid}")
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

    /**
     * 教师分页获取任务
     *
     * @param page
     * @return
     */
    @PostMapping("getByPageAdmin")
    public Result<Page> getByPageAdmin(@RequestBody Page page) {
        page.setIndex(page.getIndex());
        page = taskService.getByPageAdmin(page);
        return new Result<>(page);
    }

    /**
     * 获取总人数
     *
     * @return
     */
    @GetMapping("getUserNum")
    public Result getUserNum() {
        int i = userMapper.selectUserNum();
        return new Result<>(i);
    }

    /**
     * 保存转为发布
     *
     * @param id
     * @return
     */
    @PutMapping("saveTopush/{id}/{time}")
    public Result savedTopushed(@PathVariable("id") Integer id, @PathVariable("time") Date date) {
        taskService.savedTopushed(id, date);
        return new Result<>(ResultEnum.SUCCESS);
    }

    /**
     * 删除转发布
     *
     * @param task
     * @return
     */
    @PostMapping("deleteTopush")
    public Result deleteTopush(@RequestBody Task task) {
        task.setPushedTime(new Date());
        taskService.delete(task.getTaskId());
        task.setIsEnabled(true);
        taskService.saveTask(task);
        return new Result<>(ResultEnum.SUCCESS);
    }

    /**
     * 更新通知
     *
     * @param task
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody Task task) {
        if (task.getIsEnabled() == false) {
            task.setPushedTime(null);
        } else {
            task.setPushedTime(new Date());
        }
        taskService.update(task);
        return new Result(ResultEnum.SUCCESS);
    }

    @GetMapping("gettask/{taskid}")
    public Result selectTask(@PathVariable("taskid") Integer taskid) {
        Task task = taskService.selectTask(taskid);
        return new Result(task);
    }

    @PutMapping("setlevel/{level}/{id}")
    public Result setLevel(@PathVariable("level") Integer level, @PathVariable("id") Integer id) {
        taskService.setLevel(level, id);
        return new Result(ResultEnum.SUCCESS);
    }
}
