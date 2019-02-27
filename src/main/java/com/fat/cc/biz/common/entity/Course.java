package com.fat.cc.biz.common.entity;

import com.fat.cc.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Course extends BaseEntity {

    //所属教师id
    private Integer teacherId;

    private String name;

    //简介
    private String introduction;

    //本课程学费
    private BigDecimal fee;

    @Transient
    private List<CourseFiles> courseFilesList;


}
