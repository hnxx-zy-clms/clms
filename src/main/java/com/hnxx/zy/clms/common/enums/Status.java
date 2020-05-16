package com.hnxx.zy.clms.common.enums;

import java.util.concurrent.TimeUnit;

/**
 * @program: clms
 * @description: 状态枚举
 * @author: nile
 * @create: 2020-05-15 18:46
 **/
public abstract  class Status {

    /**
     * 过期时间相关枚举
     */
    public static enum ExpireEnum{
        //未读消息的有效期为30天
        UNREAD_MSG(30L, TimeUnit.DAYS);

        /**
         * 过期时间
         */
        private Long time;
        /**
         * 时间单位
         */
        private TimeUnit timeUnit;

        ExpireEnum(Long time, TimeUnit timeUnit) {
            this.time = time;
            this.timeUnit = timeUnit;
        }

        public Long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }
}