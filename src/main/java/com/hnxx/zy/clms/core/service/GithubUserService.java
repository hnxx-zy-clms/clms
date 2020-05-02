package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.GithubCount;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 南街北巷
 * @data 2020/5/2 19:39
 */
@Repository
@Mapper
public interface GithubUserService {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    GithubCount getById(Integer id);

    /**
     * 新增用户
     * @param githubCount
     * @return
     */
    void insertUser(GithubCount githubCount);

    /**
     * 删除用户
     * 重置自增Id
     */
    void deleteAllUser();

    /**
     * 根据id删除github用户
     * @param id
     */
    void deleteUserById(int id);
}
