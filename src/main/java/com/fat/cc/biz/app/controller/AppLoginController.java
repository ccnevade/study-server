package com.fat.cc.biz.app.controller;

import com.fat.cc.biz.common.entity.Student;
import com.fat.cc.biz.manage.service.StudentServiceImpl;
import com.fat.cc.core.review.ApiReview;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "app-login",description = "app登录")
@RequestMapping("app/login")
@RestController
public class AppLoginController {

    @Autowired
    private StudentServiceImpl studentService;

    @ApiOperation("登录")
    @PostMapping("login")
    public ApiReview login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Student student = studentService.login(username, password);
        if (null == student) {
            return ApiReview.fail("用户名或密码错误！");
        }

        return ApiReview.success(student);
    }


}
