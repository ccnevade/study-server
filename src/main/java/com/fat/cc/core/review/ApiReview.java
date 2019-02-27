package com.fat.cc.core.review;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by cc on 18/5/14
 */
@Data
public class ApiReview implements Serializable {

    private Integer code;

    private Object data;

    private String msg;

    public ApiReview(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static ApiReview success() {
        return new ApiReview(200000, null, "ok");
    }
    public static ApiReview success(Object data) {
        return new ApiReview(200000, data, "ok");
    }

    public static ApiReview success(Integer code,Object data,String msg) {
        return new ApiReview(code, data, msg);
    }

    public static ApiReview fail() {
        return new ApiReview(500000, null, "error");
    }

    public static ApiReview fail(String msg) {
        return new ApiReview(500000, null, msg);
    }

    public static ApiReview fail(Integer code,Object data,String msg) {
        return new ApiReview(code, data, msg);
    }


}
