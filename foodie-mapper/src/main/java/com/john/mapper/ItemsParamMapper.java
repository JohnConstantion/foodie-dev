package com.john.mapper;

import com.john.pojo.ItemsParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author johnconstantine
 */
@Mapper
public interface ItemsParamMapper {
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
     * @param record {@link ItemsParam}
     * @return yes or no
     */
    int insert(ItemsParam record);

    /**
     * 批量插入数据
     *
     * @param record {@link ItemsParam}
     * @return yes or no
     */
    int insertSelective(ItemsParam record);

    /**
     * 根据ID查询数据
     *
     * @param id ID
     * @return {@link ItemsParam}
     */
    ItemsParam selectByPrimaryKey(String id);

    /**
     * 根据商品ID查询商品规格
     *
     * @param itemId ID
     * @return {@link List<ItemsParam>}
     */
    List<ItemsParam> selectByItemId(String itemId);

    /**
     * 根据ID批量修改数据
     *
     * @param record {@link ItemsParam}
     * @return yes or no
     */
    int updateByPrimaryKeySelective(ItemsParam record);

    /**
     * 根据ID修改数据
     *
     * @param record {@link ItemsParam}
     * @return yes or no
     */
    int updateByPrimaryKey(ItemsParam record);
}