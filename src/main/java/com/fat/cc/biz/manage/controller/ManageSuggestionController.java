package com.fat.cc.biz.manage.controller;

import com.fat.cc.biz.common.entity.Suggestion;
import com.fat.cc.biz.manage.service.SuggestionServiceImpl;
import com.fat.cc.core.review.ApiReview;
import com.fat.cc.core.review.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "manage-suggestion",description = "意见建议管理")
@RequestMapping("manage/suggestion")
@RestController
public class ManageSuggestionController {

    @Autowired
    private SuggestionServiceImpl suggestionService;

    @ApiOperation("添加意见")
    @PostMapping("add")
    public ApiReview add(@RequestBody Suggestion suggestion) {
        suggestion.setCreateTime(new Date());
        return ApiReview.success(suggestionService.save(suggestion));
    }

    @ApiOperation("删除意见")
    @GetMapping("delete/{id}")
    public ApiReview delete(@PathVariable("id") Integer id) {
        suggestionService.deleteById(id);
        return ApiReview.success();
    }

    @ApiOperation("列表-意见")
    @PostMapping("list")
    public ApiReview list() {
        return ApiReview.success(suggestionService.list());
    }

    @ApiOperation("分页-意见")
    @PostMapping("page")
    public ApiReview page(@RequestBody SearchModel searchModel) {
        return ApiReview.success(suggestionService.page(searchModel.getPage()));
    }
}
