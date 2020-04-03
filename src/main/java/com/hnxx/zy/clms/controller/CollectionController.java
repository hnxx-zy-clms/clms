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
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Collection> delete(@PathVariable("id") Integer id){
        collectionService.deleteById(id);
        return new Result<>("删除成功!");
    }
}
