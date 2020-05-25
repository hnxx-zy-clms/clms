package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
     * 根据名字查询
     *
     * @param name
     * @return
     */
    List<User> getByName(String name);

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
     * 根据用户id或用户名查询组名
     *
     * @param user
     * @return
     */
    List<User> getByGroup(User user);
}
