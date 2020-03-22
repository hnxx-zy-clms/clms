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
    void save(Log logger);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<Log> getByPage(Page<Log> page);

    /**
     * 分页查询时统计总数
     *
     * @param page
     * @return
     */
    int getCountByPage(Page<Log> page);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id集合删除
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

}


// @Select("select\n" +
//         "        log_id, log_url, log_params, log_status, log_message,\n" +
//         "        log_method, log_time, log_result, log_ip, created_time\n" +
//         "        from bl_log\n" +
//         "        <where>\n" +
//         "            <if test=\"params.logUrl!=null and params.logUrl!=''\">\n" +
//         "                and log_url = #{params.logUrl}\n" +
//         "            </if>\n" +
//         "            <if test=\"params.logStatus!=null\">\n" +
//         "                and log_status = #{params.logStatus}\n" +
//         "            </if>\n" +
//         "            <if test=\"params.logMethod!=null and params.logMethod!=''\">\n" +
//         "                and log_method = #{params.logMethod}\n" +
//         "            </if>\n" +
//         "            <if test=\"params.logIp!=null and params.logIp!=''\">\n" +
//         "                and log_ip = #{params.logIp}\n" +
//         "            </if>\n" +
//         "        </where>\n" +
//         "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
//         "            order by ${sortColumn} ${sortMethod}\n" +
//         "        </if>\n" +
//         "        limit #{index}, #{pageSize}")
// @Results(
//         id="BaseResultMap",
//         value = {
//                 @Result(column="log_id", property = "logId"),
//                 @Result(column="log_url", property = "logUrl"),
//                 @Result(column="log_params", property = "logParams"),
//                 @Result(column="log_status", property = "logStatus"),
//                 @Result(column="log_message", property = "logMessage"),
//                 @Result(column="log_method", property = "logMethod"),
//                 @Result(column="log_time", property = "logTime"),
//                 @Result(column="log_result", property = "logResult"),
//                 @Result(column="log_ip", property = "logIp"),
//                 @Result(column="created_time", property = "createdTime"),
//
//         }
// )
// @Select("select\n" +
//         "        count(*)\n" +
//         "        from bl_log\n" +
//         "        <where>\n" +
//         "            <if test=\"params.logUrl!=null and params.logUrl!=''\">\n" +
//         "                and log_url = #{params.logUrl}\n" +
//         "            </if>\n" +
//         "            <if test=\"params.logStatus!=null\">\n" +
//         "                and log_status = #{params.logStatus}\n" +
//         "            </if>\n" +
//         "            <if test=\"params.logMethod!=null and params.logMethod!=''\">\n" +
//         "                and log_method = #{params.logMethod}\n" +
//         "            </if>\n" +
//         "            <if test=\"params.logIp!=null and params.logIp!=''\">\n" +
//         "                and log_ip = #{params.logIp}\n" +
//         "            </if>\n" +
//         "        </where>")
// @Insert("insert into cl_log(\n" +
//         "            log_url, log_params, log_status, log_message, log_method, log_time, log_result, log_ip\n" +
//         "        ) values (\n" +
//         "            #{logUrl}, #{logParams}, #{logStatus},  #{logMessage}, #{logMethod}, #{logTime},\n" +
//         "            #{logResult}, #{logIp}\n" +
//         "        )")