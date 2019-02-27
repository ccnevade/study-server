package com.fat.cc.biz.common.entity;

import com.fat.cc.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Data
public class CoursePlan extends BaseEntity {

    //课程计划名
    private String name;

    //介绍
    private String introduction;

    //课程id(','分隔)
    private String courseIds;

    @Transient
    private List<Course> courseList;


}
