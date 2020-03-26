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
    @Insert("insert into cl_report(report_type,work_content,difficulty,solution,experience,plan)" +
            "values(#{reportType},#{workContent},#{difficulty},#{solution},#{experience},#{plan})" )
    @Options(useGeneratedKeys=true, keyProperty="reportId", keyColumn="report_id")
    void save(Report report);

    /**
     * 根据修改报告
     */
    @Update("update cl_report set work_content = #{workContent},difficulty = #{difficulty},solution = #{solution},experience = #{experience},plan = #{plan} "+
            "where report_id = #{reportId} and is_enabled = 0 and is_deleted = 0")
    void update(Report report);

    /**
     * 根据id删除报告
     */
    @Delete("update into cl_report set is_deleted = 1 where report_id = #{reportId} and is_enabled = 0")
    void deleteById(Integer reportId);

    /**
     * 根据user_classes_id和report_type查找班级报告
     */
    Page<Report> getReportByClassId(Page<Report> page);

    /**
     * 根据user_group_id和report_type查找组报告
     */
    Page<Report> getReportByGroupId(Page<Report> page);

    /**
     * 根据user_id和report_type查找个人报告
     */
    @Select("select b.* from cl_user_report a left join cl_report b on a.report_id=b.report_id " +
            "where user_id = #{params.userId} and report_type = #{params.reportType} and is_deleted = 0")
    List<Report> getReportByUserId(Page<Report> page);

    /**
     *
     * @param userId
     * @param reportId
     */
    @Insert("insert into cl_user_report(user_id,report_id) value(#{userId},#{reportId})")
    void addUserReport(Integer userId,Integer reportId);


}

