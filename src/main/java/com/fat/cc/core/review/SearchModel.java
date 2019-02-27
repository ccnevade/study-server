package com.fat.cc.core.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * Created by cc on 18/6/21
 */
@Data
public class SearchModel implements Serializable {

    private Integer currPage = 1;

    private Integer pageSize;

    public Integer getPageSize() {
        if(pageSize <= 0){

            return 20;
        }
        return pageSize;
    }

    @JsonIgnore
    public Pageable getPage() {
        return new PageRequest(getCurrPage() - 1, getPageSize());
    }
}
