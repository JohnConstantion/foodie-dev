package com.john.mapper;

import com.john.pojo.ItemsImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author johnconstantine
 */
@Mapper
public interface ItemsImgMapper {
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
     * @param record {@link ItemsImg}
     * @return yes or no
     */
    int insert(ItemsImg record);

    /**
     * 批量插入数据
     *
     * @param record {@link ItemsImg}
     * @return yes or no
     */
    int insertSelective(ItemsImg record);

    /**
     * 根据ID查询数据
     *
     * @param id ID
     * @return {@link ItemsImg}
     */
    ItemsImg selectByPrimaryKey(String id);

    /**
     * 根据商品ID查询商品图片列表
     *
     * @param itemId ID
     * @return {@link List<ItemsImg> }
     */
    List<ItemsImg> selectByPrimaryItemId(String itemId);

    /**
     * 批量修改数据
     *
     * @param record {@link ItemsImg
     * @return yes or no
     */
    int updateByPrimaryKeySelective(ItemsImg record);

    /**
     * 根据ID修改数据
     *
     * @param record {@link ItemsImg}
     * @return yes or no
     */
    int updateByPrimaryKey(ItemsImg record);
}