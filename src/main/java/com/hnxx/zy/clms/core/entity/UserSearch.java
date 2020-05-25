package com.hnxx.zy.clms.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 南街北巷
 * @data 2020/5/26 3:39
 */
@Data
public class UserSearch<T> implements Serializable {

    private static final long serialVersionUID = -5177498529969801742L;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 用户列表
     */
    private List<User> users;


}
