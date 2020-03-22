package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Notice;
import com.hnxx.zy.clms.core.entity.Registration;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/19 12:59
 * @version: 1.0
 * @desc:
 */

public interface RegistrationMapper {

    /**
     * 新建签到
     *
     * @param registration
     */
    @Insert("insert into cl_registration(user_id,sign_class) values (#{registration.userId},#{registration.signClass})")
    void saveRegist(@Param("registration") Registration registration);

    /**
     * 获取用户签到情况
     *
     * @param id
     * @return
     */
    @Select("select * from cl_registration where user_id=#{id}")
    List<Registration> getRegisListById(Integer id);

    /**
     * 获取全班当日签到情况
     *
     * @param date
     * @return
     */
    @Select("SELECT a.*,b.user_name FROM cl_registration a left JOIN cl_user b ON a.user_id=b.user_id where DATE_FORMAT(a.sign_time,'%Y-%m-%d')=#{date}")
    List<Registration> getRegisListByDate(String date);
}
