package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Commission;
import com.hnxx.zy.clms.core.entity.Registration;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 12:44
 * @version: 1.0
 * @desc:
 */
public interface CommissionMapper {

    /**
     * 插入用户今日代办
     *
     * @param commission
     */
    @Insert("insert into cl_commission(user_id,com_content,is_did) values(#{commission.userId},#{commission.comContent},0) ")
    void saveCommission(@Param("commission") Commission commission);

    /**
     * 设置代办为已完成
     *
     * @param id
     */
    @Update("update cl_commission set is_did=1 where com_id=#{id}")
    void setDid(Integer id);

    /**
     * 删除代办
     *
     * @param id
     */
    @Update("update cl_commission set is_deleted=1 where com_id=#{id}")
    void deletecom(Integer id);

    /**
     * 根据日期和用户id查询代办
     *
     * @param id
     * @param time
     * @return
     */
    @Select("select * from cl_commission where user_id=#{id} and DATE_FORMAT(created_time,'%Y-%m-%d')=#{time}")
    List<Commission> getComByIdAndTime(Integer id, String time);

}

