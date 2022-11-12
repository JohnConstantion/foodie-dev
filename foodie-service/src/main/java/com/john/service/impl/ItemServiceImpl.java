package com.john.service.impl;

import com.john.mapper.ItemsImgMapper;
import com.john.mapper.ItemsMapper;
import com.john.mapper.ItemsParamMapper;
import com.john.mapper.ItemsSpecMapper;
import com.john.pojo.Items;
import com.john.pojo.ItemsImg;
import com.john.pojo.ItemsParam;
import com.john.pojo.ItemsSpec;
import com.john.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author johnconstantine
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemsMapper itemsMapper;
    private final ItemsImgMapper itemsImgMapper;
    private final ItemsParamMapper itemsParamMapper;
    private final ItemsSpecMapper itemsSpecMapper;

    public ItemServiceImpl(ItemsMapper itemsMapper, ItemsImgMapper itemsImgMapper, ItemsParamMapper itemsParamMapper, ItemsSpecMapper itemsSpecMapper) {
        this.itemsMapper = itemsMapper;
        this.itemsImgMapper = itemsImgMapper;
        this.itemsParamMapper = itemsParamMapper;
        this.itemsSpecMapper = itemsSpecMapper;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Items queryItemsById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        return itemsImgMapper.selectByPrimaryItemId(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<ItemsParam> queryItemsParamList(String itemId) {
        return itemsParamMapper.selectByItemId(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<ItemsSpec> queryItemsSpecList(String itemId) {
        return itemsSpecMapper.selectByItemId(itemId);
    }
}
