/**
 * @FileName: ArticleTypeMapper
 * @Author: code-fusheng
 * @Date: 2020/3/22 13:12
 * Description: 文章类型Mapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.ArticleType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleTypeMapper {
    /**
     * 保存添加 文章类型
     * @param articleType
     */
    @Insert("insert into cl_article_type(type_name) values (#{typeName})")
    void save(ArticleType articleType);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from cl_article_type where type_id = #{id} and is_deleted = 0")
    ArticleType getById(Integer id);

    /**
     * 前台查询所有 未删除 is_deleted = 0  已启用 is_enabled = 1
     * @return
     */
    @Select("select type_id, type_name, type_count from cl_article_type where is_deleted = 0 and is_enabled = 1 ")
    List<ArticleType> getList();

    /**
     * 后台查询所有
     * @return
     */
    @Select("select * from cl_article_type where is_deleted = 0")
    List<ArticleType> getAll();

    /**
     * 修改分类
     * @param articleType
     */
    @Update("<script>" +
            "        update cl_article_type set\n" +
            "        version = version + 1\n" +
            "        <if test=\"typeName!=null and typeName!=''\">\n" +
            "            ,type_name = #{typeName}\n" +
            "        </if>\n" +
            "        <if test=\"typeCount!=null\">\n" +
            "            ,type_count = #{typeCount}\n" +
            "        </if>\n" +
            "        where type_id = #{typeId}\n" +
            "        and version = #{version}" +
            "</script>")
    void update(ArticleType articleType);

    /**
     * 根据id删除
     * @param id
     */
    @Update("update cl_article_type set is_deleted = 1 where type_id = #{id}")
    void deleteById(Integer id);

    /**
     * 根据名称查询
     * @param typeName
     * @return
     */
    @Select("select type_id, type_name, type_count, is_deleted, version from cl_article_type " +
            "where type_name = #{typeName} and is_deleted = 0")
    ArticleType getByName(String typeName);

    /**
     * 更新启用状态
     * @param type
     */
    @Update("update cl_article_type set version = version + 1, is_enabled = #{isEnabled} and version = #{version}")
    void updateEnable(ArticleType type);
}
