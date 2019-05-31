package com.ebay.controllers;

import com.ebay.common.Result;
import com.ebay.models.GmGradeInfo;
import com.ebay.services.GmGradeInfoService;
import com.ebay.services.IimportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/gm_gradeInfo")
public class GmGradeInfoController {
    @Autowired
    private IimportService iimportService;
    @Autowired
    private GmGradeInfoService service;

    //Excel导入课程信息到数据库
    @RequestMapping("/importGradeInfoExcel")
    @ResponseBody
    public Result importGradeInfoExcel(@RequestParam("gradeInfoFile") MultipartFile gradeInfoFile) throws ParseException {
        iimportService.importGradeInfoExcel(gradeInfoFile);
        return Result.success();
    }

    //列表
    @RequestMapping("/list")
    @ResponseBody
    public Result list(Integer page,
                       Integer size) {
        List<GmGradeInfo> list = service.list(page, size);
        int count = service.count();
        return Result.build().put("list", list).put("count", count).result();
    }
}
