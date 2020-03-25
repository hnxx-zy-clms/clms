/**
 * @FileName: GoodController
 * @Author: code-fusheng
 * @Date: 2020/3/25 15:10
 * Description: 点赞控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Good;
import com.hnxx.zy.clms.core.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private GoodService goodService;

    /**
     * 点赞
     * @param good
     * @return
     */
    @PutMapping("/doGood")
    public Result<Object> doGood(@RequestBody Good good){
        goodService.doGood(good);
        return new Result<>("点赞成功!");
    }

}
