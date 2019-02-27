package com.fat.cc.biz.manage.service;

import com.fat.cc.biz.common.entity.Teacher;
import com.fat.cc.biz.manage.repository.TeacherRepository;
import com.fat.cc.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher,Integer> {


    public TeacherRepository getRepository() {
        return (TeacherRepository) super.baseRepository;
    }

    /**
     * 根据用户名和密码查找教师
     * @param account
     * @param password
     * @return
     */
    public Teacher login(String account, String password) {
        return this.getRepository().findByAccountAndPassword(account, password);
    }
}
