package com.fat.cc.biz.manage.service;

import com.fat.cc.biz.common.entity.Student;
import com.fat.cc.biz.manage.repository.StudentRepository;
import com.fat.cc.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student,Integer> {

    public StudentRepository getRepository() {
        return (StudentRepository) super.baseRepository;
    }

    /**
     * 根据用户名和密码查找
     * @param account
     * @param password
     * @return
     */
    public Student login(String account, String password) {
        return this.getRepository().findByAccountAndPassword(account, password);
    }

}
