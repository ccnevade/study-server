package com.fat.cc.biz.manage.controller;

import com.fat.cc.biz.common.entity.Course;
import com.fat.cc.biz.common.entity.CourseFiles;
import com.fat.cc.biz.manage.service.CourseFilesServiceImpl;
import com.fat.cc.biz.manage.service.CourseServiceImpl;
import com.fat.cc.core.review.ApiReview;
import com.fat.cc.core.review.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "manage-course",description = "课程管理")
@RequestMapping("manage/course")
@RestController
public class ManageCourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private CourseFilesServiceImpl courseFilesService;

    @ApiOperation("添加课程")
    @PostMapping("add")
    public ApiReview add(@RequestBody Course course) {
        course.setCreateTime(new Date());
        return ApiReview.success(courseService.save(course));

    }

    @ApiOperation("删除课程")
    @GetMapping("delete/{id}")
    public ApiReview delete(@PathVariable("id") Integer id) {
        courseService.deleteById(id);
        return ApiReview.success();
    }

    @ApiOperation("列表-课程")
    @PostMapping("list")
    public ApiReview list(){
        return ApiReview.success(courseService.list());
    }

    @ApiOperation("分页-课程")
    @PostMapping("page")
    public ApiReview page(@RequestBody SearchModel searchModel) {
        Page<Course> page = courseService.page(searchModel.getPage());
        List<Course> courseList = page.getContent();
        courseList.forEach(e->{
            List<CourseFiles> courseFilesList = courseFilesService.listCourseFilesByCourseId(e.getId());
            e.setCourseFilesList(courseFilesList);
        });
        return ApiReview.success(page);
    }


}
