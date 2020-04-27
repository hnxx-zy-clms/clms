package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.Notice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/18 14:14
 * @version: 1.0
 * @desc:
 */
@Mapper
@Repository
public interface NoticeMapper {
    /**
     * 设置已读
     *
     * @param notice
     */
    @Insert("insert into cl_notice_user(notice_id,user_id,if_read) values (#{notice.noticeId},#{notice.userId},1)")
    void setChange(@Param("notice") Notice notice);

    /**
     * 新建通知
     *
     * @param notice
     */
    @Insert("<script>"+
            "insert into cl_notice(created_id,notice_content,notice_title,is_enabled"+
            "        <if test=\"notice.pushedTime!=null\">\n" +
            "             ,pushed_time\n" +
            "        </if>" +
            ") values (#{notice.createdId},#{notice.noticeContent},#{notice.noticeTitle},#{notice.isEnabled}"+
            "        <if test=\"notice.pushedTime!=null\">\n" +
            "             ,date_format(#{notice.pushedTime}, '%Y-%m-%d %H:%i:%s')\n" +
            "        </if>" +
            ")"+
    "</script>")
    void save(@Param("notice") Notice notice);

    /**
     * 删除通知
     *
     * @param id
     */
    @Update("update cl_notice set is_deleted=1 WHERE cl_notice.notice_id=#{id}")
    void delNotice(Integer id);

    /**
     * 学生分页获取通知
     *
     * @param page
     * @param id
     * @return
     */
    @Select("SELECT a.*,IFNULL(b.if_read,0) as if_read,c.user_name from cl_notice a left JOIN cl_notice_user b ON  b.user_id=#{id} and a.notice_id=b.notice_id LEFT JOIN cl_user c ON a.created_id=c.user_id WHERE a.is_deleted =0 and a.is_enabled=1 ORDER BY a.created_time desc LIMIT #{page.index}, #{page.pageSize} ")
    List<Notice> getByPage(@Param("page") Page page, Integer id);

    /**
     * 分页获取通知
     *
     * @param page
     * @return
     */
    @Select("<script>" +
            "        SELECT a.*,b.user_name from cl_notice a left JOIN cl_user b ON a.created_id=b.user_id where 1 > 0" +
            "        <if test=\"page.params.Title!=null and page.params.Title!=''\">\n" +
            "             and a.notice_title like CONCAT('%', #{page.params.Title}, '%')\n" +
            "        </if>" +
            "        <if test=\"page.params.createdName!=null and page.params.createdName!=''\">\n" +
            "             and b.user_name like CONCAT('%', #{page.params.createdName}, '%')\n" +
            "        </if>" +
            "        <if test=\"page.params.createdTime!=null and page.params.createdTime!=''\">\n" +
            "             and a.created_time like CONCAT('%', #{page.params.createdTime}, '%')\n" +
            "        </if>" +
            "        <if test=\'page.params.statu==\"saved\"\'>\n" +
            "             and a.is_enabled=0 and a.is_deleted=0\n" +
            "        </if>" +
            "        <if test=\'page.params.statu==\"pushed\"\'>\n" +
            "             and a.is_enabled=1 and a.is_deleted=0\n" +
            "        </if>" +
            "        <if test=\'page.params.statu==\"deleted\"\'>\n" +
            "             and a.is_deleted=1\n" +
            "        </if>" +
            "        <if test=\"page.sortColumn!=null and page.sortColumn!=''\">\n" +
            "            order by ${page.sortColumn} ${page.sortMethod}\n" +
            "        </if>\n" +
            "           LIMIT #{page.index}, #{page.pageSize}"+
            "</script>")
    @Results({
            @Result(property = "numRead",
                    column = "notice_id",
                    one = @One(select = "com.hnxx.zy.clms.core.mapper.NoticeMapper.selectnum")),
            @Result(property = "noticeId",
                    column = "notice_id")
    })
    List<Notice> getByPageAdmin(@Param("page") Page page);

    /**
     * 获取已读人数
     * @param id
     * @return
     */
    @Select("select count(*) from cl_notice_user where notice_id=#{id}")
    int selectnum(@Param("id") Integer id);

    /**
     * 查询总数
     *
     * @return
     */
    @Select("<script>"+
            "        select count(*) from cl_notice a left JOIN cl_user b ON a.created_id=b.user_id  WHERE 1>0"+
            "        <if test=\"page.params.Title!=null and page.params.Title!=''\">\n" +
            "           and notice_title like CONCAT('%', #{page.params.Title}, '%')\n" +
            "        </if>" +
            "        <if test=\"page.params.createdName!=null and page.params.createdName!=''\">\n" +
            "             and b.user_name like CONCAT('%', #{page.params.createdName}, '%')\n" +
            "        </if>" +
            "        <if test=\"page.params.createdTime!=null and page.params.createdTime!=''\">\n" +
            "             and a.created_time like CONCAT('%', #{page.params.createdTime}, '%')\n" +
            "        </if>" +
            "        <if test=\'page.params.role==\"student\" \'>\n" +
            "           and is_deleted =0 and is_enabled=1" +
            "        </if>" +
            "        </script>")
    int getCountByPage(@Param("page") Page page);

    /**
     * 批量删除
     * @param params
     */
    @Update("<script>"+
                "update cl_notice set is_deleted=1 WHERE cl_notice.notice_id in "+
                    "<foreach collection='params' item='param' open='(' separator=',' close=')'>"+
                        "   #{param}"+
                    "</foreach>"+
            "</script>")
    void deleteNotices(@Param("params") Integer [] params);

    /**
     * 将已保存状态改为发布状态
     * @param id
     */
    @Update("update cl_notice set pushed_time=date_format(#{time}, '%Y-%m-%d %H:%i:%s'),is_Enabled =1 ,is_deleted=0 where notice_id = #{id}")
    void savedTopushed(@Param("id") Integer id, @Param("time") Date date);

    /**
     * 物理删除
     * @param id
     */
    @Delete("delete from cl_notice where notice_id = #{id}")
    void delete(@Param("id") Integer id);

    /**
     * 更新通知
     * @param notice
     */
    @Update("update cl_notice set created_time=#{notice.createdTime},pushed_time=#{notice.pushedTime},notice_content=#{notice.noticeContent},notice_title=#{notice.noticeTitle},is_enabled=#{notice.isEnabled},is_deleted=#{notice.isDeleted} where notice_id = #{notice.noticeId}")
    void update(@Param("notice") Notice notice);
}