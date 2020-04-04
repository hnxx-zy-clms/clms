/**
 * @FileName: XxxMapper
 * @Author: fusheng
 * @Date: 2020/3/17 13:49
 * Description: XxxMapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.Xxx;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.JDBCType;
import java.util.List;

/**
 *  1. @Mapper 使用的是 MyBatis 的 Bean
 *  2. @Repository 数据访问层组件,使用的是 Spring 的 Bean 注入，解决 ServiceImpl 注入报错的提示,
 *  3. @Repository 是 @Component 的衍生品
 *  4. @Component 把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
 * @author 25610
 */

@Mapper
@Repository
public interface XxxMapper {

    /**
     * 保存
     * @param xxx
     */
    @Insert("insert into cl_xx(xx_name) values (#{xxName})")
    void save(Xxx xxx);

    /**
     * 根据id删除(逻辑删除，对于重要的信息一律采用逻辑删除)
     * @param id
     */
    @Update("update cl_xx set is_deleted = 1 where xx_id = #{id}")
    void deleteById(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    @Update("<script>" +
            "        update cl_xx\n" +
            "        set is_deleted = 1" +
            "        where xx_id in\n" +
            "        <foreach collection=\"list\" separator=\",\" item=\"id\" open=\"(\" close=\")\">\n" +
            "            #{id}\n" +
            "        </foreach>" +
            "</script>")
    void deleteByIds(List<Integer> ids);

    /**
     * 修改
     * @param xxx
     */
    @Update("<script>" +
            "        update cl_xx set\n" +
            "        version = version + 1\n" +
            "        <if test=\"xxName!=null and xxName!=''\">\n" +
            "            ,xx_name = #{xxName}\n" +
            "        </if>\n" +
            "        where xx_id = #{xxId}\n" +
            "        and version = #{version}" +
            "</script>")
    void update(Xxx xxx);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from cl_xx where xx_id = #{id} and is_deleted = 0")
    Xxx getById(Integer id);

    /**
     * 查询所有[后台查询]
     * @return
     */
    @Select("select * from cl_xx")
    List<Xxx> getAll();

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select xx_id, xx_name, created_time, update_time, is_enabled from cl_xx\n" +
            "        where is_deleted = 0 \n" +
            "        <if test=\"params.xxName!=null and params.xxName!=''\">\n" +
            "            and xx_name like CONCAT('%', #{params.xxName}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<Xxx> getByPage(Page<Xxx> page);

    /**
     * 统计总数
     * @param page
     * @return
     */
    @Select("select count(*) from cl_xx")
    int getCountByPage(Page<Xxx> page);

    /**
     * 改变文章状态
     * @param xxx
     */
    @Update("update cl_xx set version = version + 1, is_enabled = #{isEnabled} where xx_id = #{xxId} and version = #{version}")
    void updateEnable(Xxx xxx);

}
