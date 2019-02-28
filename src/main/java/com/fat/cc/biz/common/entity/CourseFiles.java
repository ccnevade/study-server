package com.fat.cc.biz.common.entity;

import com.fat.cc.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@Data
public class CourseFiles extends BaseEntity {

    //课程id
    private Integer courseId;

    //文件名
    private String fileName;

    //文件类型
    private String fileType;

    //文件的uri
    private String fileUri;

    @Transient
    private Course course;
}
