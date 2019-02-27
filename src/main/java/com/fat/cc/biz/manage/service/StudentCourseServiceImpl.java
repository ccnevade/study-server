package com.fat.cc.biz.manage.service;

import com.fat.cc.biz.common.entity.StudentCourse;
import com.fat.cc.biz.manage.repository.StudentCourseRepository;
import com.fat.cc.core.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseServiceImpl extends BaseServiceImpl<StudentCourse,Integer> {

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public List<StudentCourse> listByStudentId(Integer id) {
        return studentCourseRepository.findByStudentId(id);
    }
}
