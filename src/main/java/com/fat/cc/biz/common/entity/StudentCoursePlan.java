package com.fat.cc.biz.common.entity;

import com.fat.cc.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 学生的教学计划
 */
@Entity
@Data
public class StudentCoursePlan extends BaseEntity {

    private Integer studentId;

    private Integer coursePlanId;

}
