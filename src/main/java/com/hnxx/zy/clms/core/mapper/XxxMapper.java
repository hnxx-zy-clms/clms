/**
 * @FileName: XxxMapper
 * @Author: fusheng
 * @Date: 2020/3/17 13:49
 * Description: XxxMapper
 */
package com.hnxx.zy.clms.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *  1. @Mapper 使用的是 MyBatis 的 Bean
 *  2. @Repository 使用的是 Spring 的 Bean 注入，解决 ServiceImpl 注入报错的提示
 * @author 25610
 */

@Mapper
@Repository
public interface XxxMapper {

    /**
     * 根据id查询Xxx实体
     * @param id
     */
    @Select("select * from cl_Xxx where id=#{id}")
    void getById(Integer id);
}
