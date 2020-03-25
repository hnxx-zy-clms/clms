package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

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
     * 根据id修改报告
     */
    @Update("update cl_report set work_content=#{workContent},difficulty=#{difficulty},solution=#{solution},experience=#{experience},plan=#{plan} "+
            "where report_id=#{reportId} and is_enabled=0 and is_deleted=0")
    void update(Report report);

    /**
     * 根据id删除报告
     */
    void deleteById(String reportId);

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
    Page<Report> getReportByUserId(Page<Report> page);

}

