/**
 * @FileName: Result
 * @Author: code-fusheng
 * @Date: 2020/3/17 21:18
 * Description: 统一返回结果
 */
package com.hnxx.zy.clms.common.utils;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 7263078016203988682L;

    /**
     * code 返回状态码
     * msg 返回信息
     * data 返回数据集
     */
    private Integer code;
    private String msg;
    private T data;

    /**
     * 默认情况下返回成功结果集
     * 说明 ： 以下情况如何理解
     * 只有msg参数，说明请求未返回结果，比如保存，修改等操作，直接对应信息
     * 害。具体情况集体分析吧
     */
    public Result() {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
    }
    public Result(String msg) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = msg;
    }
    public Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }
    public Result(String msg, T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = msg;
        this.data = data;
    }
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    /**
     * 默认失败
     * @param code
     * @param msg
     * @param data
     */
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
