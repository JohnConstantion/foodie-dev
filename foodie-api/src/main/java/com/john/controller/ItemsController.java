package com.john.controller;

import com.john.pojo.Items;
import com.john.pojo.ItemsImg;
import com.john.pojo.ItemsSpec;
import com.john.pojo.vo.CommentLevelCountsVO;
import com.john.pojo.vo.ItemInfoVO;
import com.john.service.ItemService;
import com.john.utils.IMOOCJSONResult;
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
public class ItemsController {
    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "查询商品详情", tags = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult subCat(@ApiParam(name = "itemId", value = "商品ID", required = true)
                                  @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg("商品查询异常");
        }
        Items items = itemService.queryItemsById(itemId);
        List<ItemsImg> imgs = itemService.queryItemImgList(itemId);
        List<ItemsSpec> specs = itemService.queryItemsSpecList(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(imgs);
        itemInfoVO.setItemSpecList(specs);
        itemInfoVO.setItemParams(itemService.queryItemsParamList(itemId).get(0));

        return IMOOCJSONResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价等级", tags = "查询商品评价等级", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public IMOOCJSONResult commentLevel(@ApiParam(name = "itemId", value = "商品ID", required = true)
                                        @RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }
        CommentLevelCountsVO levelCounts = itemService.queryCommentLevelCounts(itemId);
        return IMOOCJSONResult.ok(levelCounts);
    }
}
