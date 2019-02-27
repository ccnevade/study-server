package com.fat.cc.biz.manage.controller;

import com.fat.cc.biz.common.entity.Teacher;
import com.fat.cc.biz.manage.service.TeacherServiceImpl;
import com.fat.cc.core.review.ApiReview;
import com.fat.cc.core.review.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "manage-teacher",description = "教师管理")
@RequestMapping("manage/teacher")
@RestController
public class ManageTeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @ApiOperation("添加教师")
    @PostMapping("add")
    public ApiReview add(@RequestBody Teacher teacher) {
        teacher.setPassword("123");//默认密码
        teacher.setCreateTime(new Date());
        return ApiReview.success(teacherService.save(teacher));
    }

    @ApiOperation("删除教师")
    @GetMapping("delete/{id}")
    public ApiReview delete(@PathVariable("id") Integer id) {
        teacherService.deleteById(id);
        return ApiReview.success();
    }

    @ApiOperation("列表-教师")
    @PostMapping("list")
    public ApiReview list() {
        return ApiReview.success(teacherService.list());
    }

    @ApiOperation("分页-教师")
    @PostMapping("page")
    public ApiReview page(@RequestBody SearchModel searchModel) {
        return ApiReview.success(teacherService.page(searchModel.getPage()));
    }
}
