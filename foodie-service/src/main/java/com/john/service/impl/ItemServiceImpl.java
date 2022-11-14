package com.john.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.john.enums.CommentLevel;
import com.john.mapper.*;
import com.john.pojo.*;
import com.john.pojo.vo.CommentLevelCountsVO;
import com.john.pojo.vo.ItemCommentVO;
import com.john.service.ItemService;
import com.john.utils.DesensitizationUtil;
import com.john.utils.PagedGridResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Override
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("itemId", itemId);
        map.put("level", level);
        /*
         * page: 第几页
         * pageSize: 每页显示条数
         */
        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> list = itemsCommentsMapper.queryItemComments(map);
        for (ItemCommentVO vo : list) {
            vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname()));
        }
        return setterCommentsPaged(list, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Integer getCommentCounts(String itemId, Integer level) {

        ItemsComments itemsComments = new ItemsComments();
        itemsComments.setItemId(itemId);
        if (level != null) {
            itemsComments.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCounts(itemsComments);
    }

    private PagedGridResult setterCommentsPaged(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }

}
