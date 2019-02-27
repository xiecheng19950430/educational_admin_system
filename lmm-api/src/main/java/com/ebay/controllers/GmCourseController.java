package com.ebay.controllers;

import com.ebay.common.Result;
import com.ebay.common.utils.BeanUtil;
import com.ebay.models.GmCourse;
import com.ebay.services.GmCourseService;
import com.ebay.services.IimportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/gm_course")
public class GmCourseController {
    @Autowired
    private GmCourseService service;
    @Autowired
    private IimportService iimportService;


    //列表
    @RequestMapping("/list")
    @ResponseBody
    public Result getCourseList(Integer openGrade,
                                String openTerm,
                                Integer isDelete,
                                String courseName,
                                Integer page,
                                Integer size) {
        //默认非删除
        if(isDelete==null)
            isDelete=0;
        List<GmCourse> list = service.getCourseList(openGrade,openTerm,isDelete,courseName,page,size);
        int count = service.count(openGrade,openTerm,isDelete,courseName);
        return Result.build().put("list", list).put("count", count).result();
    }

    //更新基础信息
    @RequestMapping("/update")
    @ResponseBody
    public Result update(GmCourse gmCourse) {
        GmCourse old = service.findById(gmCourse.getId());
        BeanUtil.copyNotNullBean(gmCourse, old);
        int r = service.update(old);
        return Result.success();
    }

    //通过主键查询课程信息
    @RequestMapping("/findById")
    @ResponseBody
    public Result findById(int id) {
        GmCourse gmCourse = service.findById(id);
        return Result.success(gmCourse);
    }

    //逻辑删除
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(int id, Integer flag) {
        if (flag != null && flag == 1) {
            service.delete(id);
        } else {
            GmCourse old = service.findById(id);
            //初始状态为删除
            old.setIsDelete(1);
            int r = service.update(old);
        }
        return Result.success();
    }


}
