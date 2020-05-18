package com.hnxx.zy.clms.common.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @FileName: SearchPage
 * @Author: code-fusheng
 * @Date: 2020/5/18 9:25
 * @version: 1.0
 * Description:
 */

@Data
public class SearchPage<T> implements Serializable {

    private static final long serialVersionUID = -5177498529969801742L;

    private String keyword;

    private Integer pageNo;

    private Integer pageSize;

    /**
     * 数据
     */
    private List<T> list;
}
