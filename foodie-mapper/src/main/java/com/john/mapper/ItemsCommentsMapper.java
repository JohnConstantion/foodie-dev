package com.john.mapper;

import com.john.pojo.ItemsComments;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价查询
 *
 * @author johnconstantine
 */
@Mapper
public interface ItemsCommentsMapper {
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
     * @param record {@link ItemsComments}
     * @return yes or no
     */
    int insert(ItemsComments record);

    /**
     * 批量插入数据
     *
     * @param record {@link ItemsComments}
     * @return yes or no
     */
    int insertSelective(ItemsComments record);

    /**
     * 根据主键查询信息
     *
     * @param id ID
     * @return {@link ItemsComments}
     */
    ItemsComments selectByPrimaryKey(String id);

    /**
     * 根据商品ID和评价等级查询数量
     *
     * @param record {@link ItemsComments}
     * @return 评价的数量
     */
    Integer selectCounts(ItemsComments record);

    /**
     * 批量修改数据
     *
     * @param record {@link ItemsComments}
     * @return yes or no
     */
    int updateByPrimaryKeySelective(ItemsComments record);

    /**
     * 根据主键修改信息
     *
     * @param record {@link ItemsComments
     * @return yes or no
     */
    int updateByPrimaryKey(ItemsComments record);
}