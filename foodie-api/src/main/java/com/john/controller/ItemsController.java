package com.john.controller;

import com.john.pojo.Items;
import com.john.pojo.ItemsImg;
import com.john.pojo.ItemsSpec;
import com.john.pojo.vo.CommentLevelCountsVO;
import com.john.pojo.vo.ItemInfoVO;
import com.john.service.ItemService;
import com.john.utils.IMOOCJSONResult;
import com.john.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author johnconstantine
 */
@Api(value = "商品接口", tags = "商品信息展示的相关接口列表")
@RestController
@RequestMapping("/items")
public class ItemsController extends BaseController {
    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "查询商品详情", tags = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult subCat(@ApiParam(name = "itemId", value = "商品ID", required = true) @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg("商品查询异常");
        }
        Items items = itemService.queryItemsById(itemId);
        List<ItemsImg> img = itemService.queryItemImgList(itemId);
        List<ItemsSpec> specs = itemService.queryItemsSpecList(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(img);
        itemInfoVO.setItemSpecList(specs);
        itemInfoVO.setItemParams(itemService.queryItemsParamList(itemId).get(0));

        return IMOOCJSONResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价等级", tags = "查询商品评价等级", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public IMOOCJSONResult commentLevel(@ApiParam(name = "itemId", value = "商品ID", required = true) @RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }
        CommentLevelCountsVO levelCounts = itemService.queryCommentLevelCounts(itemId);
        return IMOOCJSONResult.ok(levelCounts);
    }

    @ApiOperation(value = "查询商品评价等级", tags = "查询商品评价等级", httpMethod = "GET")
    @GetMapping("/comments")
    public IMOOCJSONResult comments(@ApiParam(name = "itemId", value = "商品ID", required = true) @RequestParam String itemId, @ApiParam(name = "level", value = "评价分类") @RequestParam Integer level, @ApiParam(name = "page", value = "第几页") @RequestParam Integer page, @ApiParam(name = "pageSize", value = "每页数量") @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }
        PagedGridResult gridResult = itemService.queryPagedComments(itemId, level, page, pageSize);
        return IMOOCJSONResult.ok(gridResult);
    }
}
