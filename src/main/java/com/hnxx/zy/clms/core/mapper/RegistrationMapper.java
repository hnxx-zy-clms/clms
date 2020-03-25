package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Notice;
import com.hnxx.zy.clms.core.entity.Registration;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/19 12:59
 * @version: 1.0
 * @desc:
 */
@Mapper
@Repository
public interface RegistrationMapper {

    /**
     * 新建签到
     *
     * @param registration
     */
    @Insert("insert into cl_registration(user_id,sign_class) values (#{registration.userId},#{registration.signClass})")
    void saveRegist(@Param("registration") Registration registration);

    /**
     * 获取用户本周签到情况
     *
     * @param id
     * @return
     */
    @Select("select * from cl_registration where user_id=#{id} and sign_time>= date_sub(curdate(),INTERVAL WEEKDAY(curdate()) DAY) and sign_time<=date_sub(curdate(),INTERVAL WEEKDAY(curdate())-7 DAY)")
    List<Registration> getRegisListById(Integer id);

    /**
     * 获取全班当日签到情况
     *
     * @param date
     * @return
     */
    @Select("SELECT a.*,b.user_name FROM cl_registration a left JOIN cl_user b ON a.user_id=b.user_id where DATE_FORMAT(a.sign_time,'%Y-%m-%d')=#{date}")
    List<Registration> getRegisListByDate(String date);

    /**
     * 取消签到
     *
     * @param id
     */
    @Delete("delete from cl_registration where sign_id=#{id}")
    void deleteRegis(Integer id);
}
