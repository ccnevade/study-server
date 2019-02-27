package com.fat.cc.biz.manage.repository;

import com.fat.cc.biz.common.entity.Student;
import com.fat.cc.core.base.BaseRepository;

public interface StudentRepository extends BaseRepository<Student,Integer> {

    Student findByAccountAndPassword(String name, String password);
}
