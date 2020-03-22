/**
 * @FileName: XxxMapper
 * @Author: fusheng
 * @Date: 2020/3/17 13:49
 * Description: XxxMapper
 */
package com.hnxx.zy.clms.core.mapper;

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
     * 查询所有[后台查询]
     * @return
     */
    @Select("select * from cl_xx")
    @Results(
            id = "BaseResultMap",
            value = {
                    @Result(column = "xx_id", property = "xxId"),
                    @Result(column = "xx_name", property = "xxName"),
                    @Result(column = "created_time", property = "createdTime"),
                    @Result(column = "update_time", property = "updateTime"),
                    @Result(column = "version", property = "version"),
                    @Result(column = "is_enabled", property = "isEnabled"),
                    @Result(column = "is_deleted", property = "isDeleted")
            }
    )
    List<Xxx> getAll();

    /**
     * 保存
     * @param xxx
     */
    @Insert("insert into cl_xx(xx_name) values (#{xxName})")
    @Options(keyColumn = "xx_id", keyProperty = "xxId")
    void save(Xxx xxx);

    /**
     * 修改
     * @param xxx
     */
    @Select("")
    void update(Xxx xxx);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from cl_xx where xx_id = #{id} and is_deleted = 0")
    @ResultMap("BaseResultMap")
    Xxx getById(Integer id);

    /**
     * 根据id删除(逻辑删除，对于重要的信息一律采用逻辑删除)
     * @param id
     */
    @Update("update cl_xx set is_deleted = 1 where xx_id = #{id}")
    void deleteById(Integer id);

}
