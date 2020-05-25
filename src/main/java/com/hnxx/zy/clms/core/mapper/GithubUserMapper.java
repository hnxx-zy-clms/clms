package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.GithubCount;
import com.hnxx.zy.clms.core.entity.Xxx;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 南街北巷
 * @data 2020/5/10 0:04
 */
@Repository
@Mapper
public interface GithubUserMapper {
    /**
     * 根据用户名查询Github用户信息
     *
     * @param name
     * @return
     */
    @Select("select * from cl_github_user where name=#{name}")
    List<GithubCount> getGUserByName(String name);

    /**
     * 根据用户姓名分页查询
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select id, name, created_time, update_time, is_enabled from cl_github_user\n" +
            "        where is_deleted = 0 \n" +
            "        <if test=\"params.name!=null and params.name!=''\">\n" +
            "            and name=#{name}\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    Page<GithubCount> getByPage(Page<GithubCount> page);

    /**
     * 新增github用户
     *
     * @param githubCount
     */
    @Insert("insert into cl_github_user(name,account_id,token,created_time,update_time)\n" +
            "values(#{name},#{accountId},#{token},#{createdTime},#{updateTime})")
    void insert(GithubCount githubCount);

    /**
     * 删除所有github账号
     * 重置自增Id
     */
    @Delete("truncate table cl_github_user")
    void deleteAllGithubUser();

    /**
     * 根据ID删除github用户
     *
     * @param id
     */
    @Delete("delete from cl_github_user where id=#{id}")
    void deleteGithubUserById(int id);

}
