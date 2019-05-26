package com.ebay.controllers;

import com.ebay.common.Result;
import com.ebay.common.utils.BeanUtil;
import com.ebay.models.GmStudent;
import com.ebay.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/gm_student")
public class GmStudentController {
    @Autowired
    private GmStudentService service;
    @Autowired
    private IimportService iimportService;
    @Autowired
    private GmStudentBodyStatusService studentBodyStatusService;
    @Autowired
    private GmStudentAssessmentService studentAssessmentService;
    @Autowired
    private GmStudentSubService studentSubService;

    //列表
    @RequestMapping("/list")
    @ResponseBody
    public Result getTeacherList(Integer isDelete,
                                 String name,
                                 String studentNo,
                                 String birthday,
                                 Integer page,
                                 Integer size) {
        //默认非删除
        if (isDelete == null)
            isDelete = 0;
        List<GmStudent> list = service.getTeacherList(null, studentNo, isDelete, name, birthday, page, size);
        int count = service.count(studentNo, isDelete, name, birthday);
        return Result.build().put("list", list).put("count", count).result();
    }

    //更新基础信息
    @RequestMapping("/update")
    @ResponseBody
    public Result update(GmStudent gmStudent) {
        boolean isHas = service.findByStudentNoWithOutSelf(gmStudent.getStudentNo(), gmStudent.getId());
        if (isHas) return Result.fail("学号不得重复！");
        GmStudent old = service.findById(gmStudent.getId());
        if (ObjectUtils.isEmpty(old)) return Result.fail("学生信息不存在！");
        //修改
        BeanUtil.copyNotNullBean(gmStudent, old);
        service.update(old);
        return Result.success();
    }

    //新增
    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(GmStudent gmStudent) {
        //学号不得重复
        boolean isHas = service.findByStudentNoWithOutSelf(gmStudent.getStudentNo(), null);
        if (isHas) return Result.fail("学号不得重复！");
        gmStudent.setIsDelete(0);
        service.insert(gmStudent);
        return Result.success();
    }

    //逻辑删除
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(int id, Integer flag) {
        if (flag != null && flag == 1) {
            service.delete(id);
        } else {
            GmStudent old = service.findById(id);
            //初始状态为删除
            old.setIsDelete(1);
            int r = service.update(old);
        }
        //删除关联的角色授权
        return Result.success();
    }

    //通过主键查询学生信息
    @RequestMapping("/findById")
    @ResponseBody
    public Result findById(int id) {
        GmStudent gmStudent = service.findById(id);
        return Result.success(gmStudent);
    }

    //绑定班级学生信息
    @RequestMapping("/bindStudent")
    @ResponseBody
    public Result bindStudent(int classId) {
        GmStudent gmStudent = service.findByClassId(classId, "student");
        if (ObjectUtils.isEmpty(gmStudent)) {
            gmStudent.setRoleName("student");
            service.update(gmStudent);
        } else {
            return Result.fail("该学生已绑定该班级信息!");
        }
        return Result.success();
    }


    //Excel学生身体状况
    @RequestMapping("/import/bodystatus")
    @ResponseBody
    public Result importBodyStatus(@RequestParam MultipartFile file) {
        studentBodyStatusService.excelImport(file);
        return Result.success();
    }

    //Excel综合能力考核
    @RequestMapping("/import/assessment")
    @ResponseBody
    public Result importAssessment(@RequestParam MultipartFile file) {
        studentAssessmentService.excelImport(file);
        return Result.success();
    }

    //Excel学生信息
    @RequestMapping("/import/sub")
    @ResponseBody
    public Result importSub(@RequestParam MultipartFile file) {
        studentSubService.excelImport(file);
        return Result.success();
    }

}
