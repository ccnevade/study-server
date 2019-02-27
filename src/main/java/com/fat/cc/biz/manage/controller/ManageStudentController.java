package com.fat.cc.biz.manage.controller;

import com.fat.cc.biz.common.entity.Course;
import com.fat.cc.biz.common.entity.Student;
import com.fat.cc.biz.common.entity.StudentCourse;
import com.fat.cc.biz.manage.service.CourseServiceImpl;
import com.fat.cc.biz.manage.service.StudentCourseServiceImpl;
import com.fat.cc.biz.manage.service.StudentServiceImpl;
import com.fat.cc.core.review.ApiReview;
import com.fat.cc.core.review.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Api(value = "manage-student",description = "学生管理")
@RequestMapping("manage/student")
@RestController
public class ManageStudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private StudentCourseServiceImpl studentCourseService;

    @Autowired
    private CourseServiceImpl courseService;

    @ApiOperation("添加学生")
    @PostMapping("add")
    public ApiReview add(@RequestBody Student student) {
        student.setPassword("123");//默认密码123
        student.setCreateTime(new Date());
        return ApiReview.success(studentService.save(student));
    }

    @ApiOperation("删除学生by id")
    @GetMapping("delete/{id}")
    public ApiReview delete(@PathVariable("id") Integer id) {
        studentService.deleteById(id);
        return ApiReview.success();
    }

    @ApiOperation("获取所有学生列表")
    @PostMapping("list")
    public ApiReview list(){
        return ApiReview.success(studentService.list());
    }

    @ApiOperation("分页-学生列表")
    @PostMapping("page")
    public ApiReview page(@RequestBody SearchModel searchModel) {
        Page<Student> page = studentService.page(searchModel.getPage());
        List<Student> studentList = page.getContent();
        studentList.forEach(e->{
            List<StudentCourse> studentCourses = studentCourseService.listByStudentId(e.getId());
            studentCourses.forEach(sc->{
                Course course = courseService.findById(sc.getCourseId());
                if (null != course) {
                    sc.setCourseName(course.getName());
                }
            });
            e.setStudentCourseList(studentCourses);
        });
        return ApiReview.success(page);
    }

    @ApiOperation("删除学生课程")
    @GetMapping("deleteCourse/{id}")
    public ApiReview deleteCourse(@PathVariable("id") Integer id) {
        studentCourseService.deleteById(id);
        return ApiReview.success();
    }

    @ApiOperation("编辑学生成绩")
    @PostMapping("editAchievement/{id}/{achievement}")
    public ApiReview editAchievement(@PathVariable("id") Integer id, @PathVariable("achievement") BigDecimal achievement) {
        StudentCourse studentCourse = studentCourseService.findById(id);
        if (null == studentCourse) {
            return ApiReview.fail("课程不存在");
        }
        studentCourse.setAchievement(achievement);
        studentCourseService.update(studentCourse);
        return ApiReview.success();
    }

}
