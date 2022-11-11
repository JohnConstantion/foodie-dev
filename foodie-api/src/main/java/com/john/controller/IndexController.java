package com.john.controller;

import com.john.enums.YesOrNo;
import com.john.pojo.Carousel;
import com.john.pojo.Category;
import com.john.service.CarouselService;
import com.john.service.CategoryService;
import com.john.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "获得首页商品分类（一级分类", tags = "获得首页商品分类（一级分类", httpMethod = "GET")
    @GetMapping("/cats")
    public IMOOCJSONResult cats() {
        List<Category> categories = categoryService.queryAllRootLevelCat();
        return IMOOCJSONResult.ok(categories);
    }

}
