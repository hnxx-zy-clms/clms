package com.hnxx.zy.clms.core.mapper;

import afu.org.checkerframework.checker.igj.qual.I;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
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
            "select b.*,c.name,x.codename userGroupId,y.codename userClassesId from cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id \n"+
            "left join cl_dict x on x.type='group' and x.code = c.user_group_id \n" +
            "left join cl_dict y on y.type='classes' and y.code = c.user_classes_id \n"+
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
            "select b.*,c.user_name,x.codename userGroupId,y.codename userClassesId from cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id \n"+
            "left join cl_dict x on x.type='group' and x.code = c.user_group_id \n" +
            "left join cl_dict y on y.type='classes' and y.code = c.user_classes_id \n"+
            "where c.user_classes_id = #{params.userClassesId} and b.report_type = #{params.reportType} and b.is_deleted = 0 and b.is_checked = 1 \n" +
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
    List<Report> getReportByClassesId(Page<Report> page);

    /**
     * 根据user_group_id和report_type查找组报告
     * 根据user_group_id、username、日期和reportType查询报告
     */
    @Select({"<script> \n" +
            "select b.*,c.user_name,x.codename userGroupId,y.codename userClassesId  from cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id \n"+
            "left join cl_dict x on x.type='group' and x.code = c.user_group_id \n" +
            "left join cl_dict y on y.type='classes' and y.code = c.user_classes_id \n"+
            "where c.user_classes_id = #{params.userClassesId} and c.user_group_id = #{params.userGroupId} and b.report_type = #{params.reportType} and b.is_deleted = 0 \n" +
            "<if test=\" params.userName != null and params.userName != '' \"  > \n" +
            "and c.user_name like concat('%',#{params.userName},'%') \n" +
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
    List<Report> getReportByGroupId(Page<Report> page);

    /**
     * 根据user_id、起止日期和report_type查找个人报告
     */
    @Select({"<script> \n"+
            "select b.*,c.user_name,x.codename userGroupId,y.codename userClassesId  from cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id \n"+
            "left join cl_dict x on x.type='group' and x.code = c.user_group_id \n" +
            "left join cl_dict y on y.type='classes' and y.code = c.user_classes_id \n"+
            "where c.user_id = #{params.userId} and b.report_type = #{params.reportType} and b.is_deleted = 0 \n"+
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
     * @param page
     * @return
     */
    @Select({"<script> \n"+
            "SELECT\n" +
            "\ta.value,b.type\n" +
            "FROM\n" +
            "((\n" +
            "SELECT\n" +
            "<if test='page.params.isClasses == 0' > \n" +
            "convert(SUM( CASE c.user_group_id WHEN #{page.params.userGroupId} THEN 1 ELSE 0 END )/#{i},decimal(15,2))*100 AS 'value'\n" +
            "</if> \n"+
            "<if test='page.params.isClasses == 1' > \n" +
            "convert(SUM( CASE c.user_classes_id WHEN #{page.params.userClassesId} THEN 1 ELSE 0 END )/#{i},decimal(15,2))*100 AS 'value'\n" +
            "</if> \n"+
            "FROM\n" +
            "cl_user_report a\n" +
            "LEFT JOIN cl_report b ON a.report_id = b.report_id\n" +
            "LEFT JOIN cl_user c ON a.user_id = c.user_id\n" +
            "WHERE\n" +
            "b.report_type = #{page.params.reportType} and b.is_deleted = 0\n" +
            "<if test='page.params.reportType == 0' > \n" +
            "and date_format(b.created_time,'%Y-%m-%d') = #{page.params.time} \n" +
            "</if> \n"+
            "<if test='page.params.reportType  == 1' > \n" +
            "and b.created_time &gt;= #{page.params.time[0]} \n" +
            "and b.created_time &lt;= #{page.params.time[1]} \n" +
            "</if> \n"+
            ") a,\n" +
            "(\n" +
            "SELECT\n" +
            "<if test='page.params.isClasses == 0' > \n" +
            "DISTINCT(x.codename) AS type \n" +
            "</if> \n"+
            "<if test='page.params.isClasses == 1' > \n" +
            "DISTINCT(y.codename) AS type \n" +
            "</if> \n"+
            "FROM\n" +
            "cl_user c\n" +
            "LEFT JOIN cl_dict x ON x.type = 'group' \n" +
            "AND x.CODE = c.user_group_id\n" +
            "LEFT JOIN cl_dict y ON y.type = 'classes' \n" +
            "AND y.CODE = c.user_classes_id \n" +
            "WHERE\n" +
            "<if test='page.params.isClasses == 0' > \n" +
            "c.user_group_id = #{page.params.userGroupId} \n" +
            "</if> \n"+
            "<if test='page.params.isClasses == 1' > \n" +
            "c.user_classes_id = #{page.params.userClassesId} \n" +
            "</if> \n"+
            ") b \n" +
            ")\n" +
            "</script>"})
    ReportStatistics getTodayStatistics(Page<ReportStatistics> page,Integer i);

    @Select({"<script> \n"+
            "SELECT   '未批阅' AS  type , 未批阅  as  value ,'已提交' as state\n" +
            "FROM \n" +
            "(\n" +
            "\tSELECT\n" +
            "\t\t\tSUM( CASE b.is_checked WHEN 0 THEN 1 ELSE 0 END ) AS '未批阅'\n" +
            "\t\tFROM\n" +
            "\t\t\tcl_user_report a\n" +
            "\t\t\tLEFT JOIN cl_report b ON a.report_id = b.report_id\n" +
            "\t\t\tLEFT JOIN cl_user c ON a.user_id = c.user_id\n" +
            "WHERE\n" +
            "b.report_type = #{page.params.reportType} and b.is_deleted = 0\n" +
            "<if test='page.params.reportType == 0' > \n" +
            "and date_format(b.created_time,'%Y-%m-%d') = #{page.params.time} \n" +
            "</if> \n"+
            "<if test='page.params.reportType  == 1' > \n" +
            "and b.created_time &gt;= #{page.params.time[0]} \n" +
            "and b.created_time &lt;= #{page.params.time[1]} \n" +
            "</if> \n"+
            "\t\t\t) a\n" +
            "\t\t\tUNION\n" +
            "SELECT   '教师批阅' AS  type , 教师批阅 as  value ,'已提交' as state\n" +
            "FROM \n" +
            "(\n" +
            "\tSELECT\n" +
            "\t\t\tSUM( CASE b.is_teacher_checked WHEN 1 THEN 1 ELSE 0 END ) AS '教师批阅'\n" +
            "\t\tFROM\n" +
            "\t\t\tcl_user_report a\n" +
            "\t\t\tLEFT JOIN cl_report b ON a.report_id = b.report_id\n" +
            "\t\t\tLEFT JOIN cl_user c ON a.user_id = c.user_id\n" +
            "WHERE\n" +
            "b.report_type = #{page.params.reportType} and b.is_deleted = 0\n" +
            "<if test='page.params.reportType == 0' > \n" +
            "and date_format(b.created_time,'%Y-%m-%d') = #{page.params.time} \n" +
            "</if> \n"+
            "<if test='page.params.reportType  == 1' > \n" +
            "and b.created_time &gt;= #{page.params.time[0]} \n" +
            "and b.created_time &lt;= #{page.params.time[1]} \n" +
            "</if> \n"+
            "\t\t\t) b\n" +
            "\t\t\t\tUNION\n" +
            "SELECT   '组长批阅' AS  type , 组长批阅 as  value ,'已提交' as state\n" +
            "FROM \n" +
            "(\n" +
            "\tSELECT\n" +
            "\t\t\tSUM( CASE b.is_checked WHEN 1 THEN 1 ELSE 0 END ) AS '组长批阅'\n" +
            "\t\tFROM\n" +
            "\t\t\tcl_user_report a\n" +
            "\t\t\tLEFT JOIN cl_report b ON a.report_id = b.report_id\n" +
            "\t\t\tLEFT JOIN cl_user c ON a.user_id = c.user_id\n" +
            "WHERE\n" +
            "b.report_type = #{page.params.reportType} and b.is_deleted = 0\n" +
            "<if test='page.params.reportType == 0' > \n" +
            "and date_format(b.created_time,'%Y-%m-%d') = #{page.params.time} \n" +
            "</if> \n"+
            "<if test='page.params.reportType  == 1' > \n" +
            "and b.created_time &gt;= #{page.params.time[0]} \n" +
            "and b.created_time &lt;= #{page.params.time[1]} \n" +
            "</if> \n"+
            "\t\t\t) c\n" +
            "\t\t\t\tUNION\n" +
            "SELECT   '班长批阅' AS  type , 班长批阅 as  value ,'已提交' as state\n" +
            "FROM \n" +
            "(\n" +
            "\tSELECT\n" +
            "\t\t\tSUM(CASE b.is_classes_checked WHEN 1 THEN 1 ELSE 0 END ) AS '班长批阅'\n" +
            "\t\tFROM\n" +
            "\t\t\tcl_user_report a\n" +
            "\t\t\tLEFT JOIN cl_report b ON a.report_id = b.report_id\n" +
            "\t\t\tLEFT JOIN cl_user c ON a.user_id = c.user_id\n" +
            "WHERE\n" +
            "b.report_type = #{page.params.reportType} and b.is_deleted = 0\n" +
            "<if test='page.params.reportType == 0' > \n" +
            "and date_format(b.created_time,'%Y-%m-%d') = #{page.params.time} \n" +
            "</if> \n"+
            "<if test='page.params.reportType  == 1' > \n" +
            "and b.created_time &gt;= #{page.params.time[0]} \n" +
            "and b.created_time &lt;= #{page.params.time[1]} \n" +
            "</if> \n"+
            "\t\t\t) d\n" +
            "\t\t\t\tUNION\n" +
            "SELECT\n" +
            "\t'未提交' AS  type ,#{i}-未提交 as value ,'未提交' as state\n" +
            "FROM\n" +
            "(\n" +
            "SELECT\n" +
            "COUNT(CASE c.user_classes_id WHEN #{page.params.userClassesId} THEN 1 END) AS '未提交'\n" +
            "FROM\n" +
            "cl_user_report a\n" +
            "LEFT JOIN cl_report b ON a.report_id = b.report_id\n" +
            "LEFT JOIN cl_user c ON a.user_id = c.user_id\n" +
            "WHERE\n" +
            "b.report_type = #{page.params.reportType} and b.is_deleted = 0\n" +
            "<if test='page.params.reportType == 0' > \n" +
            "and date_format(b.created_time,'%Y-%m-%d') = #{page.params.time} \n" +
            "</if> \n"+
            "<if test='page.params.reportType  == 1' > \n" +
            "and b.created_time &gt;= #{page.params.time[0]} \n" +
            "and b.created_time &lt;= #{page.params.time[1]} \n" +
            "</if> \n"+
            ") e\n" +
            "</script>"})
    List<ReportStatistics> getMainReportInfo(Page<ReportStatistics> page,Integer i);

    /**
     * 获取报告简要批阅信息
     * @param userId
     * @return
     */
    @Select({"<script> \n"+
            "select b.report_id,b.report_type,b.created_time,\n" +
            "CASE b.is_checked+b.is_classes_checked+b.is_teacher_checked WHEN 1 THEN d.group_time WHEN 2 THEN d.monitor_time WHEN 3 THEN d.teacher_time ELSE b.updated_time END as updated_time,\n" +
            "b.is_checked+b.is_classes_checked+b.is_teacher_checked as is_checked from \n" +
            "cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id \n"+
            "left join cl_report_marking d on a.report_id = d.report_id \n"+
            "where c.user_id = #{userId} and b.is_deleted = 0 and b.is_checked = 1\n"+
            "order by b.updated_time desc\n" +
            "limit 0, 5" +
            "</script>"})
    List<Report> getMinReportInfo(Integer userId);

    /**
     * 获取日报截止时间
     * @return
     */
    @Select("select code from cl_dict where type='report' and typename='报告截止时间' and codename='日报截止时间' ")
    Integer getTime();
}

