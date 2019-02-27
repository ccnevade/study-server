package com.fat.cc.core.review;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cc on 18/6/21
 */
@Data
public class PageReview<T> implements Serializable {

    //当前页为第几页，从1开始
    private int currPage;
    //每页大小
    private int size;
    //总页数
    private int totalPages;
    //总数据
    private long totalElements;
    private List<T> content;

    public PageReview() {
        this.size = 0;
        this.totalPages = 0;
        this.totalElements = 0;
        this.content = null;
    }
}