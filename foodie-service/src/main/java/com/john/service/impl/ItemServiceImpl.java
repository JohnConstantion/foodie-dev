package com.john.service.impl;

import com.john.enums.CommentLevel;
import com.john.mapper.*;
import com.john.pojo.*;
import com.john.pojo.vo.CommentLevelCountsVO;
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
    private final ItemsCommentsMapper itemsCommentsMapper;

    public ItemServiceImpl(ItemsMapper itemsMapper, ItemsImgMapper itemsImgMapper, ItemsParamMapper itemsParamMapper, ItemsSpecMapper itemsSpecMapper, ItemsCommentsMapper itemsCommentsMapper) {
        this.itemsMapper = itemsMapper;
        this.itemsImgMapper = itemsImgMapper;
        this.itemsParamMapper = itemsParamMapper;
        this.itemsSpecMapper = itemsSpecMapper;
        this.itemsCommentsMapper = itemsCommentsMapper;
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

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public CommentLevelCountsVO queryCommentLevelCounts(String itemId) {
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.NORMAL.type);
        Integer badCounts = getCommentCounts(itemId, CommentLevel.BAD.type);
        Integer totalCounts = goodCounts + normalCounts + badCounts;
        CommentLevelCountsVO commentLevelCountsVO = new CommentLevelCountsVO();
        commentLevelCountsVO.setTotalCounts(totalCounts);
        commentLevelCountsVO.setGoodCounts(goodCounts);
        commentLevelCountsVO.setNormalCounts(normalCounts);
        commentLevelCountsVO.setBadCounts(badCounts);
        return commentLevelCountsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    Integer getCommentCounts(String itemId, Integer level) {

        ItemsComments itemsComments = new ItemsComments();
        itemsComments.setItemId(itemId);
        if (level != null) {
            itemsComments.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCounts(itemsComments);
    }

}
