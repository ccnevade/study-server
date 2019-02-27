package com.fat.cc.biz.manage.repository;

import com.fat.cc.core.base.BaseRepository;
import com.fat.cc.biz.common.entity.Course;

import java.util.List;

public interface CourseRepository extends BaseRepository<Course,Integer> {

    List<Course> findByIdIn(List<Integer> ids);
}
