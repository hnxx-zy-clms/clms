/**
 * @FileName: LogMapper
 * @Author: code-fusheng
 * @Date: 2020/3/18 11:08
 * Description: 日志Mapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogMapper {
    /**
     * 保存日志信息
     * @param logger
     */
    @Insert("insert into bl_log(\n" +
            "            log_url, log_params, log_status, log_message, log_method, log_time, log_result, log_ip\n" +
            "        ) values (\n" +
            "            #{logUrl}, #{logParams}, #{logStatus},  #{logMessage}, #{logMethod}, #{logTime},\n" +
            "            #{logResult}, #{logIp}\n" +
            "        )")
    void save(Log logger);
}
