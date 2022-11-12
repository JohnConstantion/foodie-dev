package com.john.mapper;

import com.john.pojo.Items;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author johnconstantine
 */
@Mapper
public interface ItemsMapper {
    /**
     * 根据ID删除信息
     *
     * @param id ID
     * @return yes or no
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入数据
     *
     * @param record {@link Items}
     * @return yes or no
     */
    int insert(Items record);

    /**
     * 批量插入数据
     *
     * @param record {@link Items}
     * @return yes or no
     */
    int insertSelective(Items record);

    /**
     * 根据ID查询数据
     *
     * @param id ID
     * @return {@link Items}
     */
    Items selectByPrimaryKey(String id);

    /**
     * 根据商品信息批量修改
     *
     * @param record {@link Items}
     * @return yes or no
     */
    int updateByPrimaryKeySelective(Items record);

    /**
     * 根据ID修改信息
     *
     * @param record {@link Items}
     * @return yes or no
     */
    int updateByPrimaryKey(Items record);
}