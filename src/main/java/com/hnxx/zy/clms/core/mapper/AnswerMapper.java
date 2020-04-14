/**
 * @FileName: AnswerMapper
 * @Author: code-fusheng
 * @Date: 2020/4/11 22:35
 * Description: 答复数据访问层
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Answer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerMapper {

    /**
     * 保存
     * @param answer
     */
    @Insert("insert into cl_answer(question_id, answer_content, answer_author, answer_mark) " +
            "values (#{questionId}, #{answerContent}, #{answerAuthor}, #{answerMark})")
    void save(Answer answer);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from cl_answer where is_deleted = 0 and answer_id = #{id}")
    Answer getById(Integer id);

    /**
     * 修改
     * @param answer
     */
    @Update("<script>" +
            "        update cl_answer set answer_id = #{answerId}\n" +
            "        <if test=\"questionId!=null and questionId!=''\">\n" +
            "            ,question_id = #{questionId}\n" +
            "        </if>\n" +
            "        <if test=\"answerContent!=null and answerContent!=''\">\n" +
            "            ,answer_content = #{answerContent}\n" +
            "        </if>\n" +
            "        <if test=\"answerAuthor!=null and answerAuthor!=''\">\n" +
            "            ,answer_author = #{answerAuthor}\n" +
            "        </if>\n" +
            "        <if test=\"answerMark!=null\">\n" +
            "            ,answer_mark = #{answerMark}\n" +
            "        </if>\n" +
            "        where answer_id = #{answerId}" +
            "</script>")
    void update(Answer answer);

    /**
     * 根据id删除
     * @param id
     */
    @Update("update cl_answer set is_deleted = 1 where answer_id = #{id}")
    void deletedById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select answer_id, question_id, answer_content, answer_good, answer_time, update_time, answer_author, answer_mark from cl_answer\n" +
            "        where is_deleted = 0 \n" +
            "        <if test=\"params.questionId!=null\">\n" +
            "            and question_id = #{params.questionId}\n" +
            "        </if>\n" +
            "        <if test=\"params.answerContent!=null and params.answerContent!=''\">\n" +
            "            and answer_content like CONCAT('%', #{params.answerContent}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.answerAuthor!=null and params.answerAuthor!=''\">\n" +
            "            and answer_author like CONCAT('%', #{params.answerAuthor}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.answerTime!=null\">\n" +
            "            and answer_time between #{params.answerTime[0]} and #{params.answerTime[1]}\n" +
            "        </if>\n" +
            "        <if test=\"params.answerMark!=null\">\n" +
            "            and answer_mark = #{params.answerMark}\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<Answer> getByPage(Page<Answer> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select count(*) from  cl_answer\n" +
            "        where is_deleted = 0 \n" +
            "        <if test=\"params.questionId!=null\">\n" +
            "            and question_id = #{params.questionId}\n" +
            "        </if>\n" +
            "        <if test=\"params.answerContent!=null and params.answerContent!=''\">\n" +
            "            and answer_content like CONCAT('%', #{params.answerContent}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.answerAuthor!=null and params.answerAuthor!=''\">\n" +
            "            and answer_author like CONCAT('%', #{params.answerAuthor}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.answerTime!=null\">\n" +
            "            and answer_time between #{params.answerTime[0],jdbcType=TIMESTAMP} and #{params.answerTime[1],jdbcType=TIMESTAMP}\n" +
            "        </if>\n" +
            "        <if test=\"params.answerMark!=null\">\n" +
            "            and answer_mark = #{params.answerMark}\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<Answer> page);

    /**
     * 改变答复状态
     * @param id
     * @param mark
     */
    @Update("update cl_answer set answer_mark = #{mark} where answer_id = #{id}")
    void changeAdopt(Integer id, int mark);
}
