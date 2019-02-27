package com.fat.cc.biz.manage.controller;

import com.fat.cc.biz.common.entity.CourseFiles;
import com.fat.cc.biz.manage.service.CourseFilesServiceImpl;
import com.fat.cc.core.review.ApiReview;
import com.fat.cc.core.review.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Api(value = "manage-course-files", description = "课程资源管理")
@RequestMapping("manage/courseFiles")
@RestController
public class ManageCourseFilesController {

    //保存文件的本地地址
    @Value("${upload.file.path}")
    private String PREFIX_UPLOAD_FILE_PATH;

    @Value("${upload.file.uri}")
    private String PREFIX_UPLOAD_FILE_URI;

    @Autowired
    private CourseFilesServiceImpl courseFilesService;

    @ApiOperation("上传文件")
    @PostMapping("uploadFile")
    public ApiReview uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ApiReview.fail("上传失败，文件不存在！");
        }
        //源文件名
        String originalFilename = file.getOriginalFilename();
        //后缀(.xxx)
        String subfix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //文件保存的真实位置
//        String realPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/uploadFile/";
        String realName = UUID.randomUUID().toString()+subfix;
        String realPath = PREFIX_UPLOAD_FILE_PATH + realName;
        String uri = PREFIX_UPLOAD_FILE_URI+"/file/"+realName;

        try {
            file.transferTo(new File(realPath));
//            FileUtil.fileupload(file.getBytes(),realPath,realName);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiReview.fail("文件上传失败！");
        }
        return ApiReview.success(uri);
    }

    @ApiOperation("添加课程资源")
    @PostMapping("add")
    public ApiReview add(@RequestBody CourseFiles courseFiles) {
        courseFiles.setCreateTime(new Date());
        return ApiReview.success(courseFilesService.save(courseFiles));
    }

    @ApiOperation("删除课程资源")
    @GetMapping("delete/{id}")
    public ApiReview delete(@PathVariable("id") Integer id) {
        courseFilesService.deleteById(id);
        return ApiReview.success();
    }

    @ApiOperation("列表-课程资源")
    @PostMapping("list")
    public ApiReview list(){
        return ApiReview.success(courseFilesService.list());
    }

    @ApiOperation("分页-课程资源")
    @PostMapping("page")
    public ApiReview page(@RequestBody SearchModel searchModel) {
        return ApiReview.success(courseFilesService.page(searchModel.getPage()));
    }

}
