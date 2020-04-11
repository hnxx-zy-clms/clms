/**
 * @FileName: CollectionController
 * @Author: code-fusheng
 * @Date: 2020/3/26 13:07
 * Description: 收藏控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.core.entity.Collection;
import com.hnxx.zy.clms.core.entity.Xxx;
import com.hnxx.zy.clms.core.service.CollectionService;
import com.hnxx.zy.clms.security.test.entity.SysUser;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private UserService userService;

    /**
     * 添加保存收藏
     * @param collection
     * @return
     */
    @PostMapping("/save")
    public Result<Collection> save(@RequestBody Collection collection){
        SysUser user =userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        collection.setUserId(user.getUserId());
        collectionService.save(collection);
        return new Result<>("添加成功!");
    }

    /**
     * 根据id删除 取消收藏
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Collection> delete(@PathVariable("id") Integer id){
        collectionService.deleteById(id);
        return new Result<>("删除成功!");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<Collection> get(@PathVariable("id") Integer id){
        Collection collection = collectionService.getById(id);
        return new Result<>(collection);
    }

    /**
     * 根据用户id查询收藏列表
     * @return
     */
    @GetMapping("/getList")
    public Result<List<Collection>> getList(){
        SysUser user =userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        int id = user.getUserId();
        List<Collection> collectionList = collectionService.getListByUserId(id);
        return new Result<>(collectionList);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<Collection>> getByPage(@RequestBody Page<Collection> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        // 下划线的 排序列
        page.setSortColumn(newSortColumn);
        // 判断排序列不为空
        if(StringUtils.isNotBlank(sortColumn)){
            // 响应时间，请求时间 排序
            String[] sortColumns = {"collection_time"};
            // Arrays.asList() 方法使用
            // 1. 该方法是将数组转换成list。 Json 数据格式中的 排序列为数组形式，此处需要转换成 List数据形式
            // 2. 该方法不适用于剧本数据类型（byte,short,int,long,float,double,boolean）
            // 3. 不支持add和remove方法
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"参数错误！");
            }
        }
        page = collectionService.getByPage(page);
        return new Result<>(page);
    }


}
