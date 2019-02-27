package com.fat.cc.biz.common.entity;

import com.fat.cc.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Student extends BaseEntity {

    private String name;

    //1-男 2-女
    private Integer sex;

    private Integer age;

    //学号
    private String account;

    //密码
    private String password;

    //专业
    private String major;

    //班级
    private String classroom;

    //学费
    private BigDecimal tuition = BigDecimal.ZERO;

    @Transient
    private List<StudentCourse> studentCourseList;



}
