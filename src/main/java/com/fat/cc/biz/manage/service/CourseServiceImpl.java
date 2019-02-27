package com.fat.cc.biz.manage.service;

import com.fat.cc.biz.common.entity.Course;
import com.fat.cc.biz.manage.repository.CourseRepository;
import com.fat.cc.core.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, Integer> {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> listInIds(List<Integer> ids) {
        return courseRepository.findByIdIn(ids);
    }

}
