package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 南街北巷
 * @data 2020/6/3 3:55
 */
@Mapper
@Repository
public interface RoleMapper {

    /**
     * 分页查询
     *
     * @return
     */
    @Select("select * from cl_role group by role_id")
    List<Role> getAllRole();
}
