/**
 * @FileName: MessageController
 * @Author: code-fusheng
 * @Date: 2020/3/17 13:18
 * Description: 模版控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.core.entity.Message;
import com.hnxx.zy.clms.core.service.MessageService;
import com.hnxx.zy.clms.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author code-fusheng
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    /**
     * 保存
     * @param message
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Message message){
        messageService.save(message);
        return new Result<>("保存成功!");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable("id") Integer id){
        messageService.deleteById(id);
        return new Result<>("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PutMapping("/deleteByIds")
    public Result<Object> deleteByIds(@RequestBody List<Integer> ids){
        messageService.deleteByIds(ids);
        return new Result<>("删除成功！");
    }

    /**
     * 根据id确认消息
     * @return
     */
    @PutMapping("confirmMessageById/{id}")
    public Result<Object> confirmMessageById(@PathVariable("id") Integer id){
        messageService.confirmMessageById(id);
        return new Result<>("确认消息！");
    }

    /**
     * 批量确认消息
     * @return
     */
    @PutMapping("confirmMessageByIds")
    public Result<Object> confirmMessageByIds(@RequestBody List<Integer> ids){
        messageService.confirmMessageByIds(ids);
        return new Result<>("确认消息！");
    }



    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<Message> get(@PathVariable("id") Integer id){
        Message message = messageService.getById(id);
        return new Result<>(message);
    }

    /**
     * 查询当前用户消息列表
     * @return
     */
    @GetMapping("/getList")
    public Result<List<Message>> getList(){
        String user = userService.getUserName();
        List<Message> messageList = messageService.getList(user);
        return new Result<>(messageList);
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/getAll")
    public Result<List<Message>> getAll(){
        List<Message> messageList = messageService.getAll();
        return new Result<>(messageList);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<Message>> getByPage(@RequestBody Page<Message> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        // 下划线的 排序列
        page.setSortColumn(newSortColumn);
        // 判断排序列不为空
        if(StringUtils.isNotBlank(sortColumn)){
            // 消息创建时间和修改时间
            String[] sortColumns = {"created_time", "update_time"};
            // Arrays.asList() 方法使用
            // 1. 该方法是将数组转换成list。 Json 数据格式中的 排序列为数组形式，此处需要转换成 List数据形式
            // 2. 该方法不适用于剧本数据类型（byte,short,int,long,float,double,boolean）
            // 3. 不支持add和remove方法
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"参数错误！");
            }
        }
        page = messageService.getByPage(page);
        return new Result<>(page);
    }
}
