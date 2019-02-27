package com.fat.cc.biz.manage.service;

import com.fat.cc.biz.common.entity.CourseFiles;
import com.fat.cc.biz.manage.repository.CourseFilesRepository;
import com.fat.cc.core.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseFilesServiceImpl extends BaseServiceImpl<CourseFiles,Integer> {

    @Autowired
    private CourseFilesRepository courseFilesRepository;

    public List<CourseFiles> listCourseFilesByCourseId(Integer courseId) {
        return courseFilesRepository.findByCourseId(courseId);
    }
}
