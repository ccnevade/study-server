package com.fat.cc.biz.app.controller;

import com.fat.cc.biz.common.entity.Suggestion;
import com.fat.cc.biz.manage.service.SuggestionServiceImpl;
import com.fat.cc.core.review.ApiReview;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(value = "app-suggestion", description = "意见或建议")
@RequestMapping("app/suggestion")
@RestController
public class AppSuggestionController {

    @Autowired
    private SuggestionServiceImpl suggestionService;

    @ApiOperation("提交建议")
    @PostMapping("suggest")
    public ApiReview suggest(@RequestParam("title")String title,@RequestParam("content")String content,@RequestParam("author")String author) {
        Suggestion suggestion = new Suggestion(title, content, author);
        suggestion.setCreateTime(new Date());
        suggestionService.save(suggestion);
        return ApiReview.success();
    }
}
