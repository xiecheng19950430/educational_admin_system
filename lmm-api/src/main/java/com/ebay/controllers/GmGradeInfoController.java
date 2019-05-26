package com.ebay.controllers;

import com.ebay.common.Result;
import com.ebay.services.IimportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

@Controller
@RequestMapping("/gm_gradeInfo")
public class GmGradeInfoController {
    @Autowired
    private IimportService iimportService;

    //Excel导入课程信息到数据库
    @RequestMapping("/importGradeInfoExcel")
    @ResponseBody
    public Result importGradeInfoExcel(@RequestParam("gradeInfoFile") MultipartFile gradeInfoFile) throws ParseException {
        iimportService.importGradeInfoExcel(gradeInfoFile);
        return Result.success();
    }
}
