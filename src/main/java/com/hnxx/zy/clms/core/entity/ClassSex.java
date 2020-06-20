package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author WisdomBao
 * @Date 2020/6/20 18:25
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassSex {

    /**
     * 性别
     */
    private String sex;

    /**
     * 百分比
     */
    private double percent;
}
