package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.entity.ReportMarking;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: clms
 * @description: 报告批阅mapper
 * @author: nile
 * @create: 2020-03-24 16:14
 **/
@Mapper
@Repository
public interface ReportMarkingMapper {

    /**
     * 管理员获取报告批阅信息
     * @param page
     * @return
     */
    @Select({"<script> \n" +
            "select a.* from cl_report b left join cl_report_marking a on a.report_id = b.report_id  \n" +
            "where b.is_deleted = 0 \n" +
            "<if test=\" params.markingType != null and params.markingType != '' \"  > \n" +
            "<if test=\" params.markingType == 3 \"> \n" +
            "and b.is_checked = 1 \n" +
            "</if> \n" +
            "<if test=\" params.markingType == 1 \"> \n" +
            "and b.is_classes_checked = 1 \n" +
            "</if> \n" +
            "<if test=\" params.markingType == 2 \"> \n" +
            "and b.is_teacher_checked = 1 \n" +
            "</if> \n" +
            "</if> \n" +
            "<if test=\" params.reportType !=null and params.reportType !='' \"  > \n" +
            "and b.report_type = #{params.reportType}\n" +
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
    List<ReportMarking> getAllMarking(Page<ReportMarking> page);

    /**
     *  管理员根据id清空批阅数据
     *   不可恢复
     * @param markingId
     */
    @Update("update cl_report_marking a left join cl_report b on a.report_id = b.report_id set  group_leader_score = null,\n" +
            "        group_leader_comment = null,\n" +
            "        group_name = null,\n" +
            "        group_time = null,\n" +
            "        monitor_score = null,\n" +
            "        monitor_comment = null,\n" +
            "        monitor_name = null,\n" +
            "        monitor_time = null,\n" +
            "        teacher_score = null,\n" +
            "        teacher_comment = null,\n" +
            "        teacher_name = null,\n" +
            "        teacher_time = null,\n" +
            "        b.is_checked = 0,b.is_classes_checked = 0,b.is_teacher_checked = 0 where marking_id = #{markingId} ")
    void deleteAdminById(Integer markingId);

    /**
     * 返回用户未批阅的报告
     * @param page
     * @return
     */
    @Select({"<script> \n" +
            "select b.*,c.name,x.codename userGroupId,y.codename userClassesId  from cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id \n"+
            "left join cl_dict x on x.type='group' and x.code = c.user_group_id \n" +
            "left join cl_dict y on y.type='classes' and y.code = c.user_classes_id \n"+
            "where b.is_enabled = 1 and b.report_type = #{params.reportType} and b.is_deleted = 0 \n"+
            "<if test=\" params.UserPositionId == 1 \"  > \n" +
            "and b.is_checked = 0 and  c.user_classes_id = #{params.UserClassesId} and c.user_group_id = #{params.UserGroupId}\n" +
            "</if> \n"+
            "<if test=\" params.UserPositionId == 2 \"  > \n" +
            "and b.is_checked = 1 and b.is_classes_checked = 0 and c.user_classes_id = #{params.UserClassesId}\n" +
            "</if> \n"+
            "<if test=\" params.UserPositionId == 3 \"  > \n" +
            "and b.is_checked = 1 and b.is_classes_checked = 1 and b.is_teacher_checked = 0" +
            "</if> \n"+
            "order by ${sortColumn} ${sortMethod}\n" +
            "</script>"})
    List<Report> getNotMarkingReport(Page<Report> page);

    /**
     * 用户名获取批阅信息
     * @param page
     * @return
     */
    @Select({"<script> \n" +
            "select b.*,c.name,x.codename userGroupId,y.codename userClassesId  from cl_user_report a left join cl_report b on a.report_id = b.report_id left join cl_user c on a.user_id = c.user_id \n"+
            "left join cl_dict x on x.type='group' and x.code = c.user_group_id \n" +
            "left join cl_dict y on y.type='classes' and y.code = c.user_classes_id \n"+
            "where b.is_enabled = 1 and b.report_type = #{params.reportType} and b.is_deleted = 0 \n"+
            "<if test=\" params.UserPositionId == 1 \"  > \n" +
            "and b.is_checked = 1 and c.user_group_id = #{params.UserGroupId}\n" +
            "</if> \n"+
            "<if test=\" params.UserPositionId == 2 \"  > \n" +
            "and b.is_checked = 1 and b.is_classes_checked = 1\n" +
            "</if> \n"+
            "<if test=\" params.UserPositionId == 3 \"  > \n" +
            "and b.is_checked = 1 and b.is_classes_checked = 1 and b.is_teacher_checked = 1" +
            "</if> \n"+
            "order by ${sortColumn} ${sortMethod}\n" +
            "</script>"})
    List<Report> getMarkingReport(Page<Report> page);

    /**
     * 组长提交批阅报告
     * @param reportMarkings
     */
    @Update({"<script> \n" +
            "<foreach collection='reportMarkings' item='item' index='index' separator=';' > \n" +
            "update cl_report_marking set\n"+
            "group_leader_score = #{item.groupLeaderScore},group_leader_comment = #{item.groupLeaderComment},group_name = #{item.groupName} ,group_time = #{item.groupTime}\n" +
            " where report_id = #{item.reportId} \n" +
            "</foreach> \n" +
            "</script>"})
    void setGroupMarking(@Param("reportMarkings") List<ReportMarking> reportMarkings);

    /**
     * 班长提交批阅报告
     * @param reportMarkings
     */
    @Update({"<script> \n" +
            "<foreach collection='reportMarkings' item='item' index='index' separator=';' > \n" +
            "update cl_report_marking set \n"+
            "monitor_score = #{item.groupLeaderScore},monitor_comment = #{item.groupLeaderComment},monitor_name = #{item.groupName} ,monitor_time = #{item.groupTime}\n" +
            " where report_id = #{item.reportId}\n" +
            "</foreach> \n" +
            "</script>"})
    void setClassesMarking(@Param("reportMarkings") List<ReportMarking> reportMarkings);

    /**
     * 教师提交批阅报告
     * @param reportMarkings
     */
    @Update({"<script> \n" +
            "<foreach collection='reportMarkings' item='item' index='index' separator=';' > \n" +
            "update cl_report_marking set \n"+
            " teacher_score = #{item.groupLeaderScore} ,teacher_comment = #{item.groupLeaderComment},teacher_name = #{item.groupName} ,teacher_time = #{item.groupTime}\n" +
            " where report_id = #{item.reportId} \n" +
            "</foreach> \n" +
            "</script>"})
    void setTeacherMarking(@Param("reportMarkings") List<ReportMarking> reportMarkings);


    /**
     * 学生报告ID查询报告批阅
     * @param id
     * @return
     */
    @Select({"<script> \n" +
            "SELECT * FROM cl_report_marking \n" +
            "WHERE report_id = #{id} \n"+
            "</script>"})
    ReportMarking getUserMarkingById(Integer id);

    @Select({"<script> \n" +
            "SELECT\n" +
            "\t  #{params.time} as  type,'平均' as state ,AVG( a.group_leader_score) as value\n" +
            "FROM\n" +
            "\tcl_report_marking a\n" +
            "\tLEFT JOIN cl_report b ON a.report_id = b.report_id\n" +
            "\tLEFT JOIN cl_user_report c ON b.report_id = c.report_id\n" +
            "\tLEFT JOIN cl_user d ON c.user_id = d.user_id\n" +
            "\twhere b.is_deleted = 0 AND b.report_type = 0 AND  date_format(b.created_time,'%Y-%m-%d') = #{params.time}"+
            "</script>"})
    ReportStatistics getAvgReportScore(Page<ReportStatistics> page);

    @Select({"<script> \n" +
            "SELECT\n" +
            "\t #{params.time} as  type,#{params.userName} as state ,a.group_leader_score as value\n" +
            "FROM\n" +
            "\tcl_report_marking a\n" +
            "\tLEFT JOIN cl_report b ON a.report_id = b.report_id\n" +
            "\tLEFT JOIN cl_user_report c ON b.report_id = c.report_id\n" +
            "\tLEFT JOIN cl_user d ON c.user_id = d.user_id\n" +
            "\twhere b.is_deleted = 0 AND b.report_type = 0 AND  date_format(b.created_time,'%Y-%m-%d') = #{params.time}"+
            "\tAND d.name = #{params.userName} "+
            "</script>"})
    ReportStatistics getReportScore(Page<ReportStatistics> page);

}
