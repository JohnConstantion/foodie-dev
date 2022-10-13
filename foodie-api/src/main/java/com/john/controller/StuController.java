package com.john.controller;

import com.john.service.StuService;
import org.springframework.web.bind.annotation.*;

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
    public Object getStuInfo(@RequestParam int id) {
        return stuService.getStuInfo(id);
    }

    @PostMapping("/save_stu")
    public Object saveStu() {
        stuService.insert();
        return "ok";
    }

    @PostMapping("/update_stu")
    public Object updateStu(@RequestParam int id) {
        stuService.updateById(id);
        return "ok";
    }

    @PostMapping("/delete_stu")
    public Object deleteStu(@RequestParam int id) {
        stuService.deleteById(id);
        return "ok";
    }

}
