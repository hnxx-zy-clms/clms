package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Registration;
import com.hnxx.zy.clms.core.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/19 13:06
 * @version: 1.0
 * @desc:
 */
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private static Logger logger = LoggerFactory.getLogger(NoticeController.class);


    @Autowired
    private RegistrationService registrationService;

    /**
     * 新建签到
     *
     * @param registration
     * @return
     */
    @PostMapping("/saveregist")
    public Result saveRegis(@RequestBody Registration registration) {

        registrationService.saveRegist(registration);
        return new Result<>(ResultEnum.SUCCESS);
    }

    /**
     * 获取签到
     *
     * @param id
     * @return
     */
    @GetMapping("/getuserregis/{id}")
    public Result getRegisListById(@PathVariable("id") Integer id) {
        List<Registration> regis = registrationService.getRegisListById(id);
        return new Result(regis);
    }

    /**
     * 获取该日签到情况
     *
     * @param date
     * @return
     */
    @GetMapping("/getregis/{date}")
    public Result getRisListByDate(@PathVariable("date") String date) {
        List<Registration> regis = registrationService.getRegisListByDate(date);
        return new Result(regis);
    }

}
