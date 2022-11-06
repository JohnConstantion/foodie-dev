package com.john.service.impl;

import com.john.mapper.CarouselMapper;
import com.john.pojo.Carousel;
import com.john.service.CarouselService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author johnconstantine
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    private final CarouselMapper carouselMapper;

    public CarouselServiceImpl(CarouselMapper carouselMapper) {
        this.carouselMapper = carouselMapper;
    }

    @Override
    public List<Carousel> queryAll(Integer isShow) {
        return carouselMapper.selectByIsShow(isShow);
    }
}
