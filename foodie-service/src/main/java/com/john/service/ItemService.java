package com.john.service;

import com.john.pojo.Items;
import com.john.pojo.ItemsImg;
import com.john.pojo.ItemsParam;
import com.john.pojo.ItemsSpec;

import java.util.List;

/**
 * 商品详情
 *
 * @author johnconstantine
 */
public interface ItemService {
    /**
     * 根据ID查询商品详情
     *
     * @param itemId ID
     * @return {@link Items}
     */
    Items queryItemsById(String itemId);

    /**
     * 根据商品ID查询商品图片列表
     *
     * @param itemId ID
     * @return {@link ItemsImg
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品ID查询商品规格
     *
     * @param itemId ID
     * @return {@link ItemsParam}
     */
    List<ItemsParam> queryItemsParamList(String itemId);

    /**
     * 根据商品ID查询商品参数
     *
     * @param itemId ID
     * @return {@link ItemsSpec}}
     */
    List<ItemsSpec> queryItemsSpecList(String itemId);
}
