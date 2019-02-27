package com.fat.cc.biz.manage.controller;

import com.fat.cc.biz.common.entity.*;
import com.fat.cc.biz.manage.service.CoursePlanServiceImpl;
import com.fat.cc.biz.manage.service.CourseServiceImpl;
import com.fat.cc.biz.manage.service.StudentCoursePlanServiceImpl;
import com.fat.cc.biz.manage.service.StudentCourseServiceImpl;
import com.fat.cc.core.review.ApiReview;
import com.fat.cc.core.review.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "manage-course-plan", description = "课程计划管理")
@RequestMapping("manage/coursePlan")
@RestController
public class ManageCoursePlanController {

    @Autowired
    private CoursePlanServiceImpl coursePlanService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private StudentCourseServiceImpl studentCourseService;

    @Autowired
    private StudentCoursePlanServiceImpl studentCoursePlanService;

    @ApiOperation("添加教学计划")
    @PostMapping("add")
    public ApiReview add(@RequestBody CoursePlan coursePlan) {
        coursePlan.setCreateTime(new Date());
        return ApiReview.success(coursePlanService.save(coursePlan));
    }

    @ApiOperation("删除教学计划")
    @GetMapping("delete/{id}")
    public ApiReview delete(@PathVariable("id") Integer id) {
        coursePlanService.deleteById(id);
        return ApiReview.success();
    }

    @ApiOperation("列表-教学计划")
    @PostMapping("list")
    public ApiReview list() {
        return ApiReview.success(coursePlanService.list());
    }

    @ApiOperation("分页-教学计划")
    @PostMapping("page")
    public ApiReview page(@RequestBody SearchModel searchModel) {
        Page<CoursePlan> page = coursePlanService.page(searchModel.getPage());
        List<CoursePlan> coursePlanList = page.getContent();
        coursePlanList.forEach(e->{
            if (null != e.getCourseIds()) {
                List<String> ids = Arrays.asList(e.getCourseIds().split(","));
                List newIds = new ArrayList();
                for (String s : ids) {
                    newIds.add(Integer.valueOf(s));
                }
                List<Course> courseList = courseService.listInIds(newIds);
                e.setCourseList(courseList);
            }
        });
        return ApiReview.success(page);
    }

    @ApiOperation("添加课程")
    @PostMapping("addCourses/{id}")
    public ApiReview addCourses(@PathVariable("id") Integer id, @RequestBody List<Course> courseList) {
        CoursePlan coursePlan = coursePlanService.findById(id);
        if (null == coursePlan) {
            return ApiReview.fail("教学计划不存在");
        }
        List<Integer> ids = courseList.stream().map(Course::getId).collect(Collectors.toList());
        String idsStr = StringUtils.join(ids, ",");
        coursePlan.setCourseIds(idsStr);
        coursePlanService.update(coursePlan);
        return ApiReview.success();
    }

    @ApiOperation("添加学生")
    @PostMapping("addStudents/{id}")
    public ApiReview addStudents(@PathVariable("id")Integer id, @RequestBody List<Student> studentList) {
        CoursePlan coursePlan = coursePlanService.findById(id);
        if (null == coursePlan) {
            return ApiReview.fail("教学计划不存在");
        }

        List<String> ids = Arrays.asList(coursePlan.getCourseIds().split(","));
        List newIds = new ArrayList();
        for (String s : ids) {
            newIds.add(Integer.valueOf(s));
        }
        List<Course> courseList = courseService.listInIds(newIds);


        studentList.forEach(e->{
            //计划添加学生
            StudentCoursePlan studentCoursePlan = new StudentCoursePlan();
            studentCoursePlan.setCoursePlanId(id);
            studentCoursePlan.setStudentId(e.getId());
            studentCoursePlan.setCreateTime(new Date());
            studentCoursePlanService.save(studentCoursePlan);
            courseList.forEach(c->{
                //为学生添加课程
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setStudentId(e.getId());
                studentCourse.setCourseId(c.getId());
                studentCourse.setCreateTime(new Date());
                studentCourseService.save(studentCourse);
            });
        });

        return ApiReview.success();
    }


}
