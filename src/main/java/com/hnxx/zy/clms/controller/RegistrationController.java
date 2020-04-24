package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Registration;
import com.hnxx.zy.clms.core.service.RegistrationService;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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

    private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);


    @Autowired
    private RegistrationService registrationService;

    /**
     * 新建签到
     *
     * @param registration
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Registration registration) {
        Registration registration1 = registrationService.selectSignClass(registration.getUserId(), registration.getSignTime());
        if (registration1 == null) {
            registrationService.saveRegist(registration);
        } else {
            registrationService.updateRegistration(registration1.getSignClass() + registration.getSignClass(), registration1.getSignId());
        }

        return new Result<>(ResultEnum.SUCCESS);
    }

    /**
     * 根据用户id获取本周签到情况
     *
     * @param id
     * @return
     */
    @GetMapping("/getListById/{id}")
    public Result getListById(@PathVariable("id") Integer id) {
        List<Registration> regis = registrationService.getRegisListById(id);
        return new Result(regis);
    }

    @GetMapping("/getList")
    public Result getRegisList() {
        List<Registration> registrations = registrationService.getRegisList();
        return new Result(registrations);
    }

    /**
     * 获取该日签到情况
     *
     * @param date
     * @return
     */
    @GetMapping("/getListByDate/{date}")
    public Result getListByDate(@PathVariable("date") String date) {
        List<Registration> regis = registrationService.getRegisListByDate(date);
        return new Result(regis);
    }

    /**
     * 删除签到
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        registrationService.deleteRegis(id);
        return new Result("取消成功");
    }

    @GetMapping("/getNameList/{classes}/{date}")
    public Result getNameList(@PathVariable("classes") Integer classes, @PathVariable("date") String date) {
        List<Registration> registrations = registrationService.getNameList(classes, date);
        return new Result(registrations);
    }

}
