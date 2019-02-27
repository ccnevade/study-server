package com.fat.cc.biz.manage.repository;

import com.fat.cc.biz.common.entity.CourseFiles;
import com.fat.cc.core.base.BaseRepository;

import java.util.List;

public interface CourseFilesRepository extends BaseRepository<CourseFiles,Integer> {

    List<CourseFiles> findByCourseId(Integer cid);
}
