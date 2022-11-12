package com.john.controller;

import com.john.enums.YesOrNo;
import com.john.pojo.Carousel;
import com.john.pojo.Category;
import com.john.pojo.vo.CategoryVO;
import com.john.pojo.vo.NewItemsVO;
import com.john.service.CarouselService;
import com.john.service.CategoryService;
import com.john.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author johnconstantine
 */
@RestController
@Api(value = "首页", tags = "首页获取接口列表")
@RequestMapping("/index")
public class IndexController {

    private final CarouselService carouselService;

    private final CategoryService categoryService;

    public IndexController(CarouselService carouselService, CategoryService categoryService) {
        this.carouselService = carouselService;
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "获取首页轮播图数据", tags = "获取首页轮播图数据", httpMethod = "GET")
    @GetMapping("/carousel")
    public IMOOCJSONResult carousel() {
        List<Carousel> carouselList = carouselService.queryAll(YesOrNo.YES.type);
        return IMOOCJSONResult.ok(carouselList);
    }

    @ApiOperation(value = "获得首页商品分类（一级分类)", tags = "获得首页商品分类（一级分类)", httpMethod = "GET")
    @GetMapping("/cats")
    public IMOOCJSONResult cats() {
        List<Category> categories = categoryService.queryAllRootLevelCat();
        return IMOOCJSONResult.ok(categories);
    }

    @ApiOperation(value = "获得商品子分类", tags = "获得商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public IMOOCJSONResult subCat(@PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }
        List<CategoryVO> categories = categoryService.getSubCatList(rootCatId);
        return IMOOCJSONResult.ok(categories);
    }

    @ApiOperation(value = "查询每个一级分类下最新的6条数据", tags = "查询每个一级分类下最新的6条数据", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public IMOOCJSONResult sixNewItems(@PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }
        List<NewItemsVO> sixNewItemsLazy = categoryService.getSixNewItemsLazy(rootCatId);
        return IMOOCJSONResult.ok(sixNewItemsLazy);
    }


}
