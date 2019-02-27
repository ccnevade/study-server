package com.fat.cc.biz.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fat.cc.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Teacher extends BaseEntity {

    private String name;

    //1-男 2-女
    private Integer sex;

    //职称
    private String title;

    //工号
    private String account;

    //密码
    @JsonIgnore
    private String password;

    //权限 1-超级管理员 2-普通教师
    private Integer permission = 2;

}
