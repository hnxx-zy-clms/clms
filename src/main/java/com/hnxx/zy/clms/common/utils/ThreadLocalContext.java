/**
 * @FileName: ThreadLocalContext
 * @Author: code-fusheng
 * @Date: 2020/3/18 10:42
 * Description: 本地线程全局变量
 */
package com.hnxx.zy.clms.common.utils;

import com.hnxx.zy.clms.core.entity.Log;
import lombok.Data;

@Data
public class ThreadLocalContext {
    /**
     * 日志实体
     */
    private Log logger = new Log();

    /**
     * 是否记录日志
     */
    private boolean isLog = false;

    /**
     * 线程本地内存中的变量
     */
    private static ThreadLocal<ThreadLocalContext> threadLocal = new ThreadLocal<>();

    public static ThreadLocalContext get() {
        if (threadLocal.get() == null) {
            ThreadLocalContext threadLocalContext = new ThreadLocalContext();
            threadLocal.set(threadLocalContext);
        }
        ThreadLocalContext threadLocalContext = threadLocal.get();
        return threadLocalContext;
    }

    public void remove() {
        this.logger = null;
        this.isLog = false;
        threadLocal.remove();
    }
}
