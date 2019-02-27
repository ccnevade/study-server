package com.fat.cc.biz.manage.repository;

import com.fat.cc.biz.common.entity.StudentCourse;
import com.fat.cc.core.base.BaseRepository;

import java.util.List;

public interface StudentCourseRepository extends BaseRepository<StudentCourse,Integer> {

    List<StudentCourse> findByStudentId(Integer id);
}
