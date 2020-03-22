/**
 * @FileName: ArticleTypeMapper
 * @Author: code-fusheng
 * @Date: 2020/3/22 13:12
 * Description: 文章类型Mapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.ArticleType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleTypeMapper {
    /**
     * 保存添加 文章类型
     * @param articleType
     */
    void save(ArticleType articleType);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ArticleType getById(Integer id);

    /**
     * 前台查询所有
     * @return
     */
    List<ArticleType> getList();

    /**
     * 后台查询所有
     * @return
     */
    List<ArticleType> getAll();

    /**
     * 修改分类
     */
    void update(ArticleType articleType);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据名称查询
     * @param typeName
     * @return
     */
    ArticleType getByName(String typeName);

    /**
     * 更新启用状态
     * @param type
     */
    void updateEnable(ArticleType type);
}
