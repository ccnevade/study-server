package com.fat.cc.biz.common.entity;

import com.fat.cc.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Suggestion extends BaseEntity {

    private String title;

    private String content;

    //发表意见建议者
    private String author;

    public Suggestion() {
    }

    public Suggestion(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
