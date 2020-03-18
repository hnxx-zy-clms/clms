/**
 * @FileName: ClmsException
 * @Author: code-fusheng
 * @Date: 2020/3/18 11:38
 * Description: 自定义异常
 */
package com.hnxx.zy.clms.common.exception;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import lombok.Getter;

@Getter
public class ClmsException extends RuntimeException{
    /**
     * RuntimeException 运行时异常：是那些可能在 Java 虚拟机正常运行期间抛出的异常的超类。
     * 可能在执行方法期间抛出但未被捕获的RuntimeException 的任何子类都无需在 throws 子句中进行声明。
     * 它是Exception的子类。
     */

    private Integer errorCode = ResultEnum.ERROR.getCode();

    public ClmsException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
    }

    public ClmsException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum.getMsg(), throwable);
        this.errorCode = resultEnum.getCode();
    }

    public ClmsException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public ClmsException(String msg) {
        super(msg);
    }

    public ClmsException(Throwable throwable) {
        super(throwable);
    }

    public ClmsException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
