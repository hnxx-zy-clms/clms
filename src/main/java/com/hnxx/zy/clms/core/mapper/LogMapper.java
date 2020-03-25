/**
 * @FileName: LogMapper
 * @Author: code-fusheng
 * @Date: 2020/3/18 11:08
 * Description: 日志Mapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Log;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogMapper {
    /**
     * 保存日志信息
     *
     * @param logger
     */
    @Insert("insert into cl_log(log_url, log_params, log_status, log_message, log_method, log_time, log_result, log_ip) " +
            "values (#{logUrl}, #{logParams}, #{logStatus},  #{logMessage}, #{logMethod}, #{logTime}, #{logResult}, #{logIp})")
    void save(Log logger);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Select("<script>select\n" +
            "        log_id, log_url, log_params, log_status, log_message,\n" +
            "        log_method, log_time, log_result, log_ip, created_time\n" +
            "        from cl_log\n" +
            "        <where>\n" +
            "            <if test=\"params.logUrl!=null and params.logUrl!=''\">\n" +
            "                and log_url = #{params.logUrl}\n" +
            "            </if>\n" +
            "            <if test=\"params.logId!=null\">\n" +
            "                and log_id = #{params.logId}\n" +
            "            </if>\n" +
            "            <if test=\"params.logStatus!=null\">\n" +
            "                and log_status = #{params.logStatus}\n" +
            "            </if>\n" +
            "            <if test=\"params.logMethod!=null and params.logMethod!=''\">\n" +
            "                and log_method = #{params.logMethod}\n" +
            "            </if>\n" +
            "            <if test=\"params.logIp!=null and params.logIp!=''\">\n" +
            "                and log_ip = #{params.logIp}\n" +
            "            </if>\n" +
            "        </where>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<Log> getByPage(Page<Log> page);

    /**
     * 分页查询时统计总数
     *
     * @param page
     * @return
     */
    @Select("<script>select\n" +
            "        count(*)\n" +
            "        from cl_log\n" +
            "        <where>\n" +
            "            <if test=\"params.logUrl!=null and params.logUrl!=''\">\n" +
            "                and log_url = #{params.logUrl}\n" +
            "            </if>\n" +
            "            <if test=\"params.logStatus!=null\">\n" +
            "                and log_status = #{params.logStatus}\n" +
            "            </if>\n" +
            "            <if test=\"params.logMethod!=null and params.logMethod!=''\">\n" +
            "                and log_method = #{params.logMethod}\n" +
            "            </if>\n" +
            "            <if test=\"params.logIp!=null and params.logIp!=''\">\n" +
            "                and log_ip = #{params.logIp}\n" +
            "            </if>\n" +
            "        </where>" +
            "</script>")
    int getCountByPage(Page<Log> page);

    /**
     * 根据id删除
     * @param id
     */
    @Delete("delete from cl_log where log_id = #{id}")
    void deleteById(Integer id);

    /**
     * 根据id集合删除
     * @param ids
     */
    @Delete("<script>" +
            "        delete from cl_log\n" +
            "        where log_id in\n" +
            "        <foreach collection=\"list\" separator=\",\" item=\"id\" open=\"(\" close=\")\">\n" +
            "            #{id}\n" +
            "        </foreach>" +
            "</script>")
    void deleteByIds(List<Integer> ids);

}