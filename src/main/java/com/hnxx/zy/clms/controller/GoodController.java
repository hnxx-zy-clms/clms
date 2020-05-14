/**
 * @FileName: GoodController
 * @Author: code-fusheng
 * @Date: 2020/3/25 15:10
 * Description: 点赞控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.core.entity.Good;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.service.GoodService;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @Autowired
    private UserService userService;

    /**
     * 点赞
     * @param good
     * @return
     */
    @PutMapping("/doGood")
    @Transactional(rollbackFor = Exception.class)
    public Result<Object> doGood(@RequestBody Good good){
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        good.setUserId(user.getUserId());
        goodService.doGood(good);
        return new Result<>("点赞成功!");
    }

    /**
     * 根据用户id查询点赞信息
     * @param id
     * @return
     */
    @GetMapping("/getList/{id}")
    public Result<List<Good>> getList(@PathVariable("id") Integer id){
        List<Good> goodList = goodService.getListByUserId(id);
        return new Result<>(goodList);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<Good>> getByPage(@RequestBody Page<Good> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        // 下划线的 排序列
        page.setSortColumn(newSortColumn);
        // 判断排序列不为空
        if(StringUtils.isNotBlank(sortColumn)){
            String[] sortColumns = {"good_time"};
            // Arrays.asList() 方法使用
            // 1. 该方法是将数组转换成list。 Json 数据格式中的 排序列为数组形式，此处需要转换成 List数据形式
            // 2. 该方法不适用于剧本数据类型（byte,short,int,long,float,double,boolean）
            // 3. 不支持add和remove方法
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"参数错误！");
            }
        }
        page = goodService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 根据id删除 取消点赞
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Good> delete(@PathVariable("id") Integer id){
        goodService.deleteById(id);
        return new Result<>("点赞取消!");
    }



}
