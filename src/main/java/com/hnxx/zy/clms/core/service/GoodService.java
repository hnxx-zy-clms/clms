/**
 * @FileName: GoodService
 * @Author: code-fusheng
 * @Date: 2020/3/25 14:32
 * Description: 点赞业务逻辑接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Good;

public interface GoodService {

    /**
     * 点赞
     * @param good
     */
     void doGood(Good good);

}
