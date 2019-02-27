package com.fat.cc.biz.common.entity;

import com.fat.cc.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Notice extends BaseEntity {

    private String title;

    private String content;

    //发布者
    private String publisher;

}
