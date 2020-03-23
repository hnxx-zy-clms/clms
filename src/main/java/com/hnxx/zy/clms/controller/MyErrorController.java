package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: clms
 * @description: 全局异常控制类
 * @author: nile
 * @create: 2020-03-18 12:12
 **/
@Controller
public class MyErrorController implements ErrorController {


    @RequestMapping("/error")
    @ResponseBody
    public Result<Object> handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return new Result<>(ResultEnum.NOT_FOUND);
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return new Result<>(ResultEnum.INTERNAL_SERVER_ERROR);
            } else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return new Result<>(ResultEnum.UNAUTHORIZED);
            }else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return new Result<>(ResultEnum.FORBIDDEN);
            }
        }
        assert status != null;
        return new Result<>(0,"未知错误",status.toString());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}