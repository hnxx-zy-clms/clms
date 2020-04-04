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
     * 返回本组未批阅的报告
     * @param page
     * @return
     */
    @Select("select b.*,c.user_name,c.user_group_id,c.user_classes_id from cl_user_report a left join cl_report b on a.report_id=b.report_id left join cl_user c on a.user_id=c.user_id " +
            "where c.user_classes_id = #{params.userClassesId} and c.user_group_id = #{params.userGroupId} and b.report_type = #{params.reportType} and b.is_checked = 0 and b.is_deleted = 0")
    List<Report> getGroupMarking(Page<Report> page);

    /**
     * 提交批阅报告
     * @param reportMarkings
     */
    @Insert({"<script> \n" +
            "insert into cl_report_marking(report_id,operation_type,operation_content,user_name) values\n" +
            "<foreach collection='reportMarkings' item='item' index='index' separator=',' > \n" +
            "(#{item.reportId},#{item.operationType},#{item.operationContent},#{item.userName}) \n" +
            "</foreach> \n" +
            "</script>"})
    void setGroupMarking(@Param("reportMarkings") List<ReportMarking> reportMarkings);

    /**
     * 修改报告组长批阅状态
     * @param reportId
     */
    @Update("update cl_report set is_checked  = 1 \n"+
            "where report_id = #{reportId} and is_deleted = 0")
    void setCheck(Integer reportId);

    /**
     * 修改班长报告批阅状态
     * @param reportId
     */
    @Update("update cl_report set is_classes_checked  = 1 \n"+
            "where report_id = #{reportId} and is_deleted = 0")
    void setClassesCheck(Integer reportId);
    /**
     * 修改教师报告批阅状态
     * @param reportId
     */
    @Update("update cl_report set is_teacher_checked  = 1 \n"+
            "where report_id = #{reportId} and is_deleted = 0")
    void setTeacherCheck(Integer reportId);

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
