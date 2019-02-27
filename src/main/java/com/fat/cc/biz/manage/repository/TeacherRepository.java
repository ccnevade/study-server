package com.fat.cc.biz.manage.repository;

import com.fat.cc.core.base.BaseRepository;
import com.fat.cc.biz.common.entity.Teacher;

public interface TeacherRepository extends BaseRepository<Teacher,Integer> {

    Teacher findByAccountAndPassword(String name, String password);
}
