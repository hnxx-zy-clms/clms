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

/**
 *  1. @Mapper 使用的是 MyBatis 的 Bean
 *  2. @Repository 使用的是 Spring 的 Bean 注入，解决 ServiceImpl 注入报错的提示
 * @author 25610
 */

@Mapper
@Repository
public interface XxxMapper {

    /**
     * 定义通用查询映射结果
     */
    // @Results(
    //         id = "BaseResultMap",
    //         value = {
    //                 @Result(column = "xx_id", property = "xxId"),
    //                 @Result(column = "xx_name", property = "xxName"),
    //                 @Result(column = "created_time", property = "createdTime"),
    //                 @Result(column = "update_time", property = "updateTime"),
    //                 @Result(column = "version", property = "version"),
    //                 @Result(column = "is_enabled", property = "isEnabled"),
    //                 @Result(column = "is_deleted", property = "isDeleted")
    //         }
    // )

    /**
     * 保存
     * @param xxx
     */
    @Insert("insert into cl_xx(xx_name) values(#{xxName})")
    void save(Xxx xxx);

    /**
     * 修改
     * @param xxx
     */
    void update(Xxx xxx);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from cl_xx where xx_id = #{id}")
    // @ResultMap("BaseResultMap")
    Xxx getById(Integer id);
}
