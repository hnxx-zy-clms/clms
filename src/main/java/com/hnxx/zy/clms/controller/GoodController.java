/**
 * @FileName: GoodController
 * @Author: code-fusheng
 * @Date: 2020/3/25 15:10
 * Description: 点赞控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.exception.ClmsException;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Good;
import com.hnxx.zy.clms.core.service.GoodService;
import com.hnxx.zy.clms.security.test.entity.SysUser;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
        SysUser user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
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

}
