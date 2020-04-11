/**
 * @FileName: XxxController
 * @Author: code-fusheng
 * @Date: 2020/3/17 13:18
 * Description: 模版控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.core.entity.Xxx;
import com.hnxx.zy.clms.core.service.XxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
     * 批量删除
     * @param ids
     * @return
     */
    @PutMapping("/deleteByIds")
    public Result<Object> deleteByIds(@RequestBody List<Integer> ids){
        xxxService.deleteByIds(ids);
        return new Result<>("删除成功！");
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
    public Result<Xxx> get(@PathVariable("id") Integer id){
        Xxx xxx = xxxService.getById(id);
        return new Result<>(xxx);
    }

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
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<Xxx>> getByPage(@RequestBody Page<Xxx> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        // 下划线的 排序列
        page.setSortColumn(newSortColumn);
        // 判断排序列不为空
        if(StringUtils.isNotBlank(sortColumn)){
            // 响应时间，请求时间 排序
            String[] sortColumns = {"xx_name", "created_time", "update_time"};
            // Arrays.asList() 方法使用
            // 1. 该方法是将数组转换成list。 Json 数据格式中的 排序列为数组形式，此处需要转换成 List数据形式
            // 2. 该方法不适用于剧本数据类型（byte,short,int,long,float,double,boolean）
            // 3. 不支持add和remove方法
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"参数错误！");
            }
        }
        page = xxxService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 启用
     * @param id
     * @return
     */
    @PutMapping("/enable/{id}")
    public Result<Object> enable(@PathVariable("id") Integer id) {
        xxxService.enableById(id);
        return new Result<>("启用成功");
    }

    /**
     * 弃用
     * @param id
     * @return
     */
    @PutMapping("/disable/{id}")
    public Result<Object> disable(@PathVariable("id") Integer id) {
        xxxService.disableById(id);
        return new Result<>("弃用成功");
    }



}
