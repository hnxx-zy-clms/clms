/**
 * @FileName: AnswerController
 * @Author: code-fusheng
 * @Date: 2020/4/11 22:31
 * Description: 答复控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.core.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;




}
