package com.john.service.impl;

import com.john.mapper.StuMapper;
import com.john.pojo.Stu;
import com.john.service.StuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author johnconstantine
 */
@Service
public class StuServiceImpl implements StuService {

    private final StuMapper stuMapper;

    public StuServiceImpl(StuMapper stuMapper) {
        this.stuMapper = stuMapper;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void insert() {
        Stu stu = new Stu();
        stu.setAge(19);
        stu.setName("张三");
        stuMapper.insert(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void updateById(int id) {
        Stu stu = new Stu();
        stu.setAge(18);
        stu.setName("Jack");
        stu.setId(id);
        stuMapper.updateByPrimaryKey(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void deleteById(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }
}
