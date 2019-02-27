package com.fat.cc.biz.manage.controller;

import com.fat.cc.biz.common.entity.Notice;
import com.fat.cc.biz.manage.service.NoticeServiceImpl;
import com.fat.cc.core.review.ApiReview;
import com.fat.cc.core.review.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "manage-notice",description = "公告管理")
@RequestMapping("manage/notice")
@RestController
public class ManageNoticeController {

    @Autowired
    private NoticeServiceImpl noticeService;

    @ApiOperation("添加公告")
    @PostMapping("add")
    public ApiReview add(@RequestBody Notice notice) {
        notice.setCreateTime(new Date());
        return ApiReview.success(noticeService.save(notice));
    }

    @ApiOperation("删除公告")
    @GetMapping("delete/{id}")
    public ApiReview delete(@PathVariable("id") Integer id) {
        noticeService.deleteById(id);
        return ApiReview.success();
    }

    @ApiOperation("列表-公告")
    @PostMapping("list")
    public ApiReview list() {
        return ApiReview.success(noticeService.list());
    }

    @ApiOperation("分页-公告")
    @PostMapping("page")
    public ApiReview page(@RequestBody SearchModel searchModel) {
        return ApiReview.success(noticeService.page(searchModel.getPage()));
    }
}
