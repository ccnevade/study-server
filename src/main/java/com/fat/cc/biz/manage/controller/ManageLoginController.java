package com.fat.cc.biz.manage.controller;

import com.fat.cc.biz.common.entity.Teacher;
import com.fat.cc.biz.manage.service.TeacherServiceImpl;
import com.fat.cc.core.review.ApiReview;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "manage-login",description = "后台登录")
@RequestMapping("manage/login")
@RestController
public class ManageLoginController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @ApiOperation("教师登录")
    @PostMapping("login")
    public ApiReview login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Teacher teacher = teacherService.login(username, password);
        if (null == teacher) {
            return ApiReview.fail("登录失败！");
        }
        return ApiReview.success(teacher);
    }
}
