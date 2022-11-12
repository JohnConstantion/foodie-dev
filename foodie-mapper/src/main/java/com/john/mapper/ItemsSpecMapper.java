package com.john.mapper;

import com.john.pojo.ItemsSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author johnconstantine
 */
@Mapper
public interface ItemsSpecMapper {
    /**
     * 根据ID删除数据
     *
     * @param id ID
     * @return yes or no
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入数据
     *
     * @param record {@link ItemsSpec
     * @return yes or no
     */
    int insert(ItemsSpec record);

    /**
     * 批量插入数据
     *
     * @param record {@link ItemsSpec}
     * @return yes or no
     */
    int insertSelective(ItemsSpec record);

    /**
     * 根据ID查询数据
     *
     * @param id ID
     * @return {@link ItemsSpec}}
     */
    ItemsSpec selectByPrimaryKey(String id);

    /**
     * 根据商品ID查询商品参数
     *
     * @param itemId ID
     * @return {@link List<ItemsSpec>}
     */
    List<ItemsSpec> selectByItemId(String itemId);

    /**
     * 根据ID批量修改数据
     *
     * @param record {@link ItemsSpec}
     * @return yes or no
     */
    int updateByPrimaryKeySelective(ItemsSpec record);

    /**
     * 根据ID修改数据
     *
     * @param record {@link ItemsSpec}
     * @return yes or no
     */
    int updateByPrimaryKey(ItemsSpec record);
}