package com.john.controller;

import com.john.service.StuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author johnconstantine
 */
@RestController
@RequestMapping("/api/stu")
public class StuController {

    private final StuService stuService;

    public StuController(StuService stuService) {
        this.stuService = stuService;
    }

    @GetMapping("/get_stu")
    public Object getStuInfo(int id) {
        return stuService.getStuInfo(id);
    }

    @PostMapping("/save_stu")
    public Object saveStu() {
        stuService.insert();
        return "ok";
    }

    @PostMapping("/update_stu")
    public Object updateStu(int id) {
        stuService.updateById(id);
        return "ok";
    }

    @PostMapping("/delete_stu")
    public Object deleteStu(int id) {
        stuService.deleteById(id);
        return "ok";
    }

}
