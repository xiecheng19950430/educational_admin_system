package com.ebay.controllers;

import com.ebay.common.NoGenerator;
import com.ebay.common.Result;
import com.ebay.common.utils.BeanUtil;
import com.ebay.models.GmClass;
import com.ebay.services.GmClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/gm_class")
public class GmClassController {
    @Autowired
    private GmClassService service;

    //列表
    @RequestMapping("/list")
    @ResponseBody
    public Result getClassList(String name,
                               Integer isDelete,
                               Integer status,
                               Integer year,
                               Integer page,
                               Integer size) {
        //默认非删除
        if(isDelete==null)
            isDelete=0;
        List<GmClass> list = service.getClassList(name,isDelete,status,year,page,size);
        int count = service.count(name,isDelete,status,year);
        return Result.build().put("list", list).put("count", count).result();
    }

    //新增
    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(GmClass gmClass) {
        gmClass.setIsDelete(0);
        service.insert(gmClass);
        return Result.success();
    }

    //批量生成
    @RequestMapping("/generate")
    @ResponseBody
    public Result generate(int num,String grade) {
        for (int i = 0; i < num; i++) {
            String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
            Calendar cale = null;
            cale = Calendar.getInstance();
            int year = cale.get(Calendar.YEAR);
            GmClass gmClass= new GmClass();
            gmClass.setClassNo(yearLast + NoGenerator.getRandomNum(2));
            gmClass.setGrade(grade);
            gmClass.setName(grade + "(i+1)");
            gmClass.setYear(year);
            gmClass.setAmount(50);
            gmClass.setStatus(0);
            gmClass.setIsDelete(0);
            service.insert(gmClass);
        }

        return Result.success();
    }

    //更新基础信息
    @RequestMapping("/update")
    @ResponseBody
    public Result update(GmClass gmClass) {
        GmClass old = service.findById(gmClass.getId());
        BeanUtil.copyNotNullBean(gmClass, old);
        int r = service.update(old);
        if(gmClass.getName()==old.getName()) {
            return Result.fail("修改后的班级名称重名，请前去修改！");
        }
        return Result.success();
    }

    //逻辑删除
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(int id,Integer flag) {
        if (flag != null && flag == 1) {
            service.delete(id);
        } else {
            GmClass old = service.findById(id);
            //初始状态为删除
            old.setIsDelete(1);
            int r = service.update(old);
        }
        return Result.success();
    }

}
