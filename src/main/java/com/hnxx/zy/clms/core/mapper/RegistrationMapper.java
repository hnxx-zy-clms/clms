package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Notice;
import com.hnxx.zy.clms.core.entity.Registration;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Insert("insert into cl_registration(user_id,sign_class,sign_time) values (#{registration.userId},#{registration.signClass},#{registration.signTime})")
    void saveRegist(@Param("registration") Registration registration);

    /**
     * 更新签到
     *
     * @param sign_class
     */
    @Update("update cl_registration set sign_class = #{sign_class} where sign_id=#{sign_id}")
    void updateRegistration(@Param("sign_class") String sign_class, @Param("sign_id") Integer sign_id);

    /**
     * 获取当天签到的情况
     *
     * @param user_id
     * @param sign_time
     * @return
     */
    @Select("select sign_id,sign_class from cl_registration where user_id=#{user_id} and sign_time=#{sign_time}")
    Registration selectSignClass(@Param("user_id") Integer user_id, @Param("sign_time") Date sign_time);

    /**
     * 获取用户本周签到情况
     *
     * @param id
     * @return
     */
    @Select("select * from cl_registration where user_id=#{id} and sign_time>= date_sub(curdate(),INTERVAL WEEKDAY(curdate()) DAY) and sign_time<=date_sub(curdate(),INTERVAL WEEKDAY(curdate())-6 DAY)")
    List<Registration> getRegisListById(Integer id);

    /**
     * 获取所有用户本周签到
     *
     * @return
     */
    @Select("SELECT * FROM cl_registration WHERE YEARWEEK(date_format(sign_time,'%Y-%m-%d'),1) = YEARWEEK(now(),1);")
    List<Registration> getRegisList();

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

    /**
     * 获取当天当节课签到人姓名
     *
     * @param classes
     * @param date
     * @return
     */
    @Select("select user_id from cl_registration where sign_class like concat('%', #{classes}, '%') and sign_time=#{date}")
    @Results({
            @Result(property = "userName",
                    column = "user_id",
                    one = @One(select = "com.hnxx.zy.clms.core.mapper.UserMapper.selectUserName")
            )
    })
    List<Registration> getNameList(Integer classes, String date);
}
