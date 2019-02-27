package com.fat.cc.biz.app.controller;

import com.fat.cc.biz.manage.service.NoticeServiceImpl;
import com.fat.cc.core.review.ApiReview;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "app-notice",description = "公告")
@RequestMapping("app/notice")
@RestController
public class AppNoticeController {

    @Autowired
    private NoticeServiceImpl noticeService;

    @ApiOperation("获取公告列表")
    @GetMapping("listNotice")
    public ApiReview listNotice() {
        return ApiReview.success(noticeService.list());
    }
}
