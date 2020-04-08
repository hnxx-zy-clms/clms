package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Report;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: clms
 * @description: 报告mapper
 * @author: nile
 * @create: 2020-03-24 16:14
 **/
@Mapper
@Repository
public interface ReportMapper {

    /**
     * 保存 添加报告
     */
    @Insert("insert into cl_report(report_type,work_content,difficulty,solution,experience,plan)\n" +
            "values(#{reportType},#{workContent},#{difficulty},#{solution},#{experience},#{plan})" )
    @Options(useGeneratedKeys=true, keyProperty="reportId", keyColumn="report_id")
    void save(Report report);

    /**
     * 根据report_id修改报告
     */
    @Update({"<script> \n" +
            "update cl_report set report_id = #{reportId}\n" +
            "<if test='workContent!=null' > \n" +
            ",work_content = #{workContent} \n" +
            "</if> \n" +
            "<if test='difficulty!=null' > \n" +
            ",difficulty = #{difficulty} \n" +
            "</if> \n" +
            "<if test='solution!=null' > \n" +
            ",solution = #{solution} \n" +
            "</if> \n" +
            "<if test='experience != null' > \n" +
            ",experience = #{experience} \n" +
            "</if> \n" +
            "<if test='plan!=null' > \n" +
            ",plan = #{plan} \n"+
            "</if> \n" +
            "where report_id = #{reportId} and is_enabled = 0 and is_deleted = 0 \n"+
            "</script>"})
    void update(Report report);

    /**
     * 根据id删除报告
     */
    @Update("update cl_report set is_deleted = 1 where report_id = #{reportId} and is_enabled = 0")
    void deleteById(Integer reportId);

    /**
     *  管理员根据id删除报告
     *   不可恢复
     * @param reportId
     */
    @Delete("delete from cl_report where report_id = #{reportId} ")
    void deleteAdminById(Integer reportId);

    /**
     * 管理员查看所有报告
     */
    @Select({"<script> \n" +
            "select b.*,c.user_name,c.user_group_id,c.user_classes_id from cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id \n" +
            "where b.report_type = #{params.reportType}\n" +
            "<if test=\" params.userName != null and params.userName != '' \"  > \n" +
            "and c.user_name like concat('%',#{params.userName},'%') \n" +
            "</if> \n" +
            "<if test=\" params.userGroupId !=null and params.userGroupId != '' \" > \n" +
            "and c.user_group_id = #{params.userGroupId}  \n" +
            "</if> \n" +
            "<if test=\" params.reportDate !=null and params.reportDate[1] != null  and params.reportDate[1] !='' \"  > \n" +
            "and b.created_time &lt;= #{params.reportDate[1]}\n" +
            "</if> \n" +
            "<if test=\"params.reportDate !=null and params.reportDate[0] != null  and params.reportDate[0] !='' \"  > \n" +
            "and b.created_time &gt;= #{params.reportDate[0]}\n" +
            "</if> \n"+
            "<if test=\" params.userPositionId != null and params.userPositionId !='' \"  > \n" +
            "and c.user_position_id = #{params.userPositionId}\n" +
            "</if> \n"+
            "<if test=\"sortColumn != null and sortColumn!=''\">\n" +
            "order by ${sortColumn} ${sortMethod}\n" +
            "</if>\n" +
            "</script>"})
    List<Report> getByPage(Page<Report> page);

    /**
     * 根据user_classes_id、用户名、起止日期和report_type查找班级报告
     */
    @Select({"<script> \n" +
            "select b.*,c.user_name,c.user_group_id,c.user_classes_id from cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id \n" +
            "where c.user_classes_id = #{params.userClassesId} and b.report_type = #{params.reportType} and b.is_deleted = 0 and b.is_checked = 1 \n" +
            "<if test='params.userName != null' > \n" +
            "and c.user_name like concat('%',#{params.userName},'%') \n" +
            "</if> \n" +
            "<if test='params.userGroupId !=null' > \n" +
            "and c.user_group_id = #{params.userGroupId} \n" +
            "</if> \n" +
            "<if test='params.startTime != null' > \n" +
            "and b.created_time &lt;= #{params.startTime}\n" +
            "</if> \n" +
            "<if test='params.endTime != null' > \n" +
            "and b.created_time &gt;= #{params.endTime}\n" +
            "</if> \n"+
            "<if test='params.userPositionId != null' > \n" +
            "and c.user_position_id = #{params.userPositionId}\n" +
            "</if> \n"+
            "<if test=\"sortColumn != null and sortColumn!=''\">\n" +
            "order by ${sortColumn} ${sortMethod}\n" +
            "</if>\n" +
            "</script>"})
    List<Report> getReportByClassesId(Page<Report> page);

    /**
     * 根据user_group_id和report_type查找组报告
     * 根据user_group_id、username、日期和reportType查询报告
     */
    @Select({"<script> \n" +
            "select b.*,c.user_name,c.user_group_id,c.user_classes_id  from cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id\n" +
            "where c.user_classes_id = #{params.userClassesId} and c.user_group_id = #{params.userGroupId} and b.report_type = #{params.reportType} and is_deleted = 0 \n" +
            "<if test='params.userName != null' > \n" +
            "and c.user_name like concat('%',#{params.userName},'%') \n" +
            "</if> \n" +
            "<if test='params.startTime != null' > \n" +
            "and b.created_time &lt;= #{params.startTime}\n" +
            "</if> \n" +
            "<if test='params.endTime != null' > \n" +
            "and b.created_time &gt;= #{params.endTime}\n" +
            "</if> \n"+
            "<if test=\"sortColumn != null and sortColumn!=''\">\n" +
            "order by ${sortColumn} ${sortMethod}\n" +
            "</if>\n" +
            "</script>"})
    List<Report> getReportByGroupId(Page<Report> page);

    /**
     * 根据user_id、起止日期和report_type查找个人报告
     */
    @Select({"<script> \n"+
            "select b.*,c.user_name,c.user_group_id,c.user_classes_id  from cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id\n" +
            "where c.user_id = #{params.userId} and b.report_type = #{params.reportType} and is_deleted = 0 \n"+
            "<if test='params.startTime != null' > \n" +
            "and b.created_time &lt;= #{params.startTime}\n" +
            "</if> \n" +
            "<if test='params.endTime != null' > \n" +
            "and b.created_time &gt;= #{params.endTime}\n" +
            "</if> \n"+
            "<if test=\"sortColumn != null and sortColumn!=''\">\n" +
            "order by ${sortColumn} ${sortMethod}\n" +
            "</if>\n" +
            "</script>"})
    List<Report> getReportByUserId(Page<Report> page);

    /**
     *插入cl_user_report数据
     * @param userId
     * @param reportId
     */
    @Insert("insert into cl_user_report(user_id,report_id) value(#{userId},#{reportId})")
    void addUserReport(Integer userId,Integer reportId);


    /**
     * 获取指定时间段内的所有日报
     * @param startTime
     * @param endTime
     * @return
     */
    @Select({"<script> \n"+
            "select * from cl_report where report_type = 0 and is_deleted = 0 and created_time &lt;=#{startTime} and created_time &gt;= #{endTime}\n"+
            "</script>"})
    List<Report> getToDayAllReport(String startTime,String endTime);

    /**
     * 获取指定时间段内的所有周报
     * @param startTime
     * @param endTime
     * @return
     */
    @Select({"<script> \n"+
            "select * from cl_report where report_type = 1 and is_deleted = 0 and created_time &lt;= #{startTime} and created_time &gt;= #{endTime}\n"+
            "</script>"})
    List<Report> getWeekAllReport(String startTime,String endTime);

    /**
     * 设置报告状态为NotEnable 不可删除或更改
     * @param report
     */
    @Update("update cl_report set is_enabled = 1 where report_id = #{reportId}  and is_deleted = 0")
    void setReportNotEnable(Report report);

    /**
     * 判断数据库当前是否存在报告
     * @param userId
     * @param nowToday
     * @param reportType
     * @param results
     * @return
     */
    @Select({"<script> \n"+
            "select count(*) from cl_user_report a left join cl_report b on a.report_id=b.report_id  \n" +
            " where a.user_id = #{userId} and b.report_type = #{reportType} and b.is_deleted = 0 \n" +
            "<if test='nowToday!=null' > \n" +
            "and created_time &gt;= #{nowToday}\n" +
            "</if> \n" +
            "<if test='results!=null' > \n" +
            "and b.created_time &lt;= #{results[1]}\n" +
            "and b.created_time &gt;= #{results[0]}\n" +
            "</if> \n"+
            "</script>"})
    int getTodayUserReport(Integer userId,String nowToday,Integer reportType,String[] results);

    /**
     * 报告情况
     * @param nowToday
     * @return
     */
    @Select({"<script> \n"+
            "select count(*)   from cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id\n" +
            "where b.created_time &gt;= #{nowToday} and b.report_type = 0 and is_deleted = 0 \n"+
            "</script>"})
    int getTodayStatistics(String nowToday);
}

