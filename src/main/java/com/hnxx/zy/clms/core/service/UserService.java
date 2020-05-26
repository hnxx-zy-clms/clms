package com.hnxx.zy.clms.core.service;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.entity.UserSearch;

import java.util.List;

/**
 * @author 南街北巷
 * @data 2020/5/9 9:25
 */
public interface UserService {
    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    Page<User> getByPage(Page<User> page);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    void addUser(User user);

    /**
     * 删除所有用户
     * 并重置自增Id为1
     */
    void deleteAllUser();

    /**
     * 根据ID删除用户
     *
     * @param id
     */
    void deleteUserById(Integer id);

    /**
     * 修改用户
     *
     * @param user
     */
    void updateUserById(User user);

    /**
     * 根据用户id查询组Id
     *
     * @param id
     * @return
     */
    String selectByGroupId(Integer id);

    /**
     * 根据用户id或用户名查询用户详细信息
     *
     * @param user
     * @return
     */
    List<UserSearch> getByGroup(User user);

    /**
     * 获取用户详细信息(分页)
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getUserByPage(Integer pageNum, Integer pageSize);
}
