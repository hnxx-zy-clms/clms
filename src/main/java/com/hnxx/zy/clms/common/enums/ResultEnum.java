package com.hnxx.zy.clms.common.enums;

import lombok.Getter;

/**
 * @FileName: ResultEnum
 * @Author: code-fusheng
 * @Date: 2020/3/17 20:43
 * Description: 返回结果枚举
 */
@Getter
public enum  ResultEnum {
    /**
     * 返回枚举类型，每一个枚举类型代表一个返回状态
     * 1** 信息，服务器收到请求，需要请求这继续执行操作
     * 2** 成功，操作被成功执行并接受处理
     * 3** 重定向，需要进一步的操作已完成请求
     * 4** 客户端错误，请求包汉语发错误或无法完成请求
     * 5** 服务器错误，服务在处理请求过程中发生了错误
     */
    SUCCESS(200, "操作成功！"),

    ERROR(400, "请求错误！"),
    PARAMS_ERROR(401,"参数错误！"),
    PARAMS_NULL_ERROR(402,"参数为空错误！"),
    DATA_NOT_FOUND(403,"查询失败！"),
    NOT_LOGIN(410, "账号未登陆！"),
    FORBIDDEN(403,"无权限，禁止访问"),
    UNAUTHORIZED(501,"未授权"),
    INTERNAL_SERVER_ERROR(500,"服务器故障"),
    NOT_FOUND(404,"访问连接不存在"),
    NOT_SUPPORTED(405,"方法不支持该连接方式")

    ;

    /**
     * code 响应码
     * msg 响应信息
     */
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
