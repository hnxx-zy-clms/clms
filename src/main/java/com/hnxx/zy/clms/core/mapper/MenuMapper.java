package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Menu;
import org.apache.ibatis.annotations.*;

/**
 * @program: clms
 * @description: 菜单查询Mapper
 * @author: nile
 * @create: 2020-05-05 16:30
 **/
@Mapper
public interface MenuMapper {

    @Select({"<script> \n"+
            "select * from cl_menu where user_position_id &lt;= #{role} \n"+
            "</script>"})
    Menu[] getMenuByRole(@Param("role")Integer role);
}
