package com.john.service;

import com.john.pojo.Carousel;

import java.util.List;

/**
 * @author johnconstantine
 */
public interface CarouselService {

    /**
     * 查询所有轮播图列表
     *
     * @param isShow isShow
     * @return {@link List<Carousel>}
     */
    List<Carousel> queryAll(Integer isShow);
}
