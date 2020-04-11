/**
 * @FileName: AnswerServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/4/11 22:34
 * Description: 答复业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.mapper.AnswerMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl {

    @Autowired
    private AnswerMapper answerMapper;
}
