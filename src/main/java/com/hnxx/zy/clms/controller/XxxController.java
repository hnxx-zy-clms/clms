/**
 * @FileName: XxxController
 * @Author: code-fusheng
 * @Date: 2020/3/17 13:18
 * Description: 模版控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Xxx;
import com.hnxx.zy.clms.core.service.XxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  1. Xxx 为实体类名称 例如：UserController, ArticleController
 *  2. @RestController 用于返回数据 Json 格式, @Controller 用于返回视图模型
 *  3. 控制层请使用 @RequestMapping("/xxx") 来做第一层访问路径
 * @author code-fusheng
 */
@RestController
@RequestMapping("/xxx")
public class XxxController {

    @Autowired
    private XxxService xxxService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/list")
    public Result<List<Xxx>> list(){
        List<Xxx> xxxList = xxxService.getAll();
        return new Result<>(xxxList);
    }

    /**
     * 保存
     * @param xxx
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Xxx xxx){
        xxxService.save(xxx);
        return new Result<>("保存成功!");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable("id") Integer id){
        xxxService.deleteById(id);
        return new Result<>("删除成功!");
    }

    /**
     * 修改
     * @param xxx
     * @return
     */
   @PutMapping("/update")
    public Result<Object> update(@RequestBody Xxx xxx){
        xxxService.update(xxx);
        return new Result<>("修改成功!");
   }

    /**
     * 根据id查询
     * @param id
     * @return
     */
   @GetMapping("/get/{id}")
    public  Result<Xxx> update(@PathVariable("id") Integer id){
       Xxx xxx = xxxService.getById(id);
       return new Result<>(xxx);
   }


}
