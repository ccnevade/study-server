package com.fat.cc.biz.common.entity;

import com.fat.cc.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * 学生修读的课程
 */
@Entity
@Data
public class StudentCourse extends BaseEntity {

    private Integer studentId;

    private Integer courseId;

    //成绩
    private BigDecimal achievement;

    @Transient
    private String courseName;

    @Transient
    private Course course;
}
