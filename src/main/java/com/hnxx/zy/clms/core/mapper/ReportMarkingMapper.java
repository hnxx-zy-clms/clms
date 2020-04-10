package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.entity.ReportMarking;
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
     * 管理员获取所有报告批阅信息
     * @param page
     * @return
     */
    @Select({"<script> \n" +
            "select b.* from cl_report a left join cl_report_marking b on a.report_id = b.report_id  \n" +
            "where a.is_deleted = 0 and a.is_checked = 1\n" +
            "<if test=\" params.is_checked != null and params.is_checked != '' \"  > \n" +
            "and a.is_checked = 1 \n" +
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
    List<ReportMarking> getAllMarking(Page<ReportMarking> page);

    /**
     * 返回本组未批阅的报告
     * @param page
     * @return
     */
    @Select("select b.*,c.user_name,c.user_group_id,c.user_classes_id from cl_user_report a left join cl_report b on a.report_id=b.report_id left join cl_user c on a.user_id=c.user_id " +
            "where c.user_classes_id = #{params.userClassesId} and c.user_group_id = #{params.userGroupId} and b.report_type = #{params.reportType} and b.is_checked = 0 and b.is_deleted = 0")
    List<Report> getGroupMarking(Page<Report> page);

    /**
     * 组长提交批阅报告
     * @param reportMarkings
     */
    @Update({"<script> \n" +
            "update cl_report_marking set \n"+
            "<foreach collection='reportMarkings' item='item' index='index' separator=',' > \n" +
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
            "update cl_report_marking set \n"+
            "<foreach collection='reportMarkings' item='item' index='index' separator=',' > \n" +
            "monitor_score = #{item.monitorScore},monitor_comment = #{item.monitorComment},monitor_name = #{item.monitorName} ,monitor_time = #{item.monitorTime}\n" +
            " where report_id = #{item.reportId}\n" +
            "</foreach> \n" +
            "</script>"})
    void setClassesMarking(@Param("reportMarkings") List<ReportMarking> reportMarkings);

    /**
     * 教师提交批阅报告
     * @param reportMarkings
     */
    @Update({"<script> \n" +
            "update cl_report_marking set \n"+
            "<foreach collection='reportMarkings' item='item' index='index' separator=',' > \n" +
            " teacher_score = #{item.teacherScore} ,teacher_comment = #{item.teacherComment},teacher_name = #{item.teacherName} ,teacher_time = #{item.teacherTime}\n" +
            " where report_id = #{item.reportId} \n" +
            "</foreach> \n" +
            "</script>"})
    void setTeacherMarking(@Param("reportMarkings") List<ReportMarking> reportMarkings);

    /**
     * 根据report_id和当前用户名获取批阅信息
     * @param reportId
     * @param userName
     * @return
     */
    @Select("select * from cl_report_marking where report_id = #{reportId} and user_name = #{userName}")
    List<ReportMarking> getMyMarking(Integer reportId,String userName);

    /**
     * 学生查询报告批阅
     * @param page
     * @return
     */
    @Select({"<script> \n" +
            "SELECT c.* FROM cl_user  a LEFT JOIN cl_user_report b on a.user_id = b.user_id LEFT JOIN cl_report_marking c on b.report_id = c.report_id LEFT JOIN cl_report d on d.report_id = b.report_id\n" +
            "WHERE a.user_id = #{params.userId} AND  d.report_type = #{params.reportType}\n"+
            "<if test='params.startTime!=null' > \n" +
            "and c.created_time &lt;= #{params.startTime}\n" +
            "</if> \n" +
            "<if test='params.endTime!=null' > \n" +
            "and c.created_time &gt;= #{params.endTime}\n" +
            "</if> \n"+
            "<if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "order by ${sortColumn} ${sortMethod}\n" +
            "</if>\n" +
            "</script>"})
    List<ReportMarking> getUserMarking(Page<ReportMarking> page);

}
