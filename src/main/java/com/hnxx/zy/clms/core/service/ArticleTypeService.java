/**
 * @FileName: ArticleTypeService
 * @Author: code-fusheng
 * @Date: 2020/3/22 13:10
 * Description: 文章类型业务逻辑接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.ArticleStatistics;
import com.hnxx.zy.clms.core.entity.ArticleType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ArticleTypeService {
    /**
     * 保存添加文件类型
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
     * @param articleType
     */
    void update(ArticleType articleType);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id启用
     * @param id
     */
    void enabledById(Integer id);

    /**
     * 根据id弃用
     * @param id
     */
    void disabledById(Integer id);

    /**
     * 查询各类型对应的做品数 以及 占比
     * @param page
     * @return
     */
    Page<ArticleStatistics> getArticleTypeCountInfo(Page<ArticleStatistics> page);
}
