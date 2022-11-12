package com.john.mapper;

import com.john.pojo.Category;
import com.john.pojo.vo.CategoryVO;
import com.john.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author johnconstantine
 */
@Mapper
public interface CategoryMapper {
    /**
     * 根据ID删除数据
     *
     * @param id ID
     * @return yes or no
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     *
     * @param record {@link Category}
     * @return yes or no
     */
    int insert(Category record);

    /**
     * 批量插入
     *
     * @param record {@link Category}
     * @return yes or no
     */
    int insertSelective(Category record);

    /**
     * 根据ID查询数据
     *
     * @param id ID
     * @return {@link Category}
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * 根据类型查询数据
     *
     * @param type 分类类型
     * @return {@link Category}
     */
    List<Category> selectByType(Integer type);

    /**
     * 批量修改数据
     *
     * @param record {@link Category}
     * @return yes or no
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * 根据ID修改数据
     *
     * @param record {@link Category}
     * @return yes or no
     */
    int updateByPrimaryKey(Category record);

    /**
     * 根据一级分类查询商品
     *
     * @param rootCatId 一级分类
     * @return 首页商品展示 {@link List<CategoryVO>}
     */
    List<CategoryVO> getSubCatList(int rootCatId);

    /**
     * 根据一级分类懒加载6个商品数据
     *
     * @param map 参数
     * @return 6个数据 {@link List<NewItemsVO>}
     */
    List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}