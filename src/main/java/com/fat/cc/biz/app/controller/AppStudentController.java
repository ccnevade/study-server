package com.fat.cc.biz.app.controller;

import com.fat.cc.biz.common.entity.Course;
import com.fat.cc.biz.common.entity.CourseFiles;
import com.fat.cc.biz.common.entity.Student;
import com.fat.cc.biz.common.entity.StudentCourse;
import com.fat.cc.biz.manage.service.CourseFilesServiceImpl;
import com.fat.cc.biz.manage.service.CourseServiceImpl;
import com.fat.cc.biz.manage.service.StudentCourseServiceImpl;
import com.fat.cc.biz.manage.service.StudentServiceImpl;
import com.fat.cc.core.review.ApiReview;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "app-student",description = "学生相关信息")
@RequestMapping("app/student")
@RestController
public class AppStudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private StudentCourseServiceImpl studentCourseService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private CourseFilesServiceImpl courseFilesService;

    @ApiOperation("根据学生id获取课程信息")
    @GetMapping("listStudentCourse/{id}")
    public ApiReview listStudentCourse(@PathVariable("id") Integer id) {
        Student student = studentService.findById(id);
        if (null == student) {
            return ApiReview.fail("学生不存在");
        }
        List<StudentCourse> studentCourseList = studentCourseService.listByStudentId(student.getId());
        studentCourseList.forEach(e->{
            Course course = courseService.findById(e.getCourseId());
            e.setCourse(course);
        });
        return ApiReview.success(studentCourseList);
    }

    @ApiOperation("根据课程id获取课程资料")
    @GetMapping("listCourseFile/{id}")
    public ApiReview listCourseFile(@PathVariable("id") Integer id) {
        List<CourseFiles> courseFiles = courseFilesService.listCourseFilesByCourseId(id);
        courseFiles.forEach(e->{
            Course course = courseService.findById(e.getCourseId());
            e.setCourse(course);
        });
        return ApiReview.success(courseFiles);
    }

    @ApiOperation("获取学生学费")
    @GetMapping("fee/{id}")
    public ApiReview fee(@PathVariable("id") Integer id) {

        List<StudentCourse> studentCourseList = studentCourseService.listByStudentId(id);
        studentCourseList.forEach(e->{
            Course course = courseService.findById(e.getCourseId());
            e.setCourse(course);
        });

        List<Course> collect = studentCourseList.stream().map(StudentCourse::getCourse).collect(Collectors.toList());
        BigDecimal reduce = collect.stream().map(Course::getFee).reduce(BigDecimal.ZERO, BigDecimal::add);
        return ApiReview.success(reduce);
    }



}
