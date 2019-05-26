package com.ebay.services;

import com.ebay.common.DateUtil;
import com.ebay.common.utils.BeanUtil;
import com.ebay.common.utils.StringUtils;
import com.ebay.mappers.GmStudentSubMapper;
import com.ebay.models.*;
import com.ebay.templete.StudentSubTemplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class GmStudentSubService {
    @Autowired
    private GmStudentSubMapper mapper;
    @Autowired
    private GmStudentService studentService;
    @Autowired
    private GmClassService classService;

    public int insert(GmStudentSub sub) {
        return mapper.insert(sub);
    }

    public boolean verifyWithOutSelf(String studentNo, String semester, Integer id) {
        return mapper.verifyWithOutSelf(studentNo, semester, id);
    }

    public GmStudentSub findByNoAndSemester(String studentNo, String semester) {
        return mapper.findByNoAndSemester(studentNo, semester);
    }

    public int update(GmStudentSub sub) {
        return mapper.update(sub);
    }

    public void excelImport(MultipartFile file) {
        List<GmStudentSub> subs = StudentSubTemplete.temp(file);
        if (!CollectionUtils.isEmpty(subs)) {
            subs.forEach(sub -> {
                GmStudentSub old = this.findByNoAndSemester(sub.getStudentNo(), sub.getSemester());
                if (ObjectUtils.isEmpty(old)) {
                    this.insert(sub);
                } else {
                    BeanUtil.copyNotNullBean(sub, old);
                    this.update(old);
                }
                //查找对应学生信息 更新 没有则插入
                GmStudent student = studentService.findByStudentNo(sub.getStudentNo());
                if (ObjectUtils.isEmpty(student)) {
                    student = new GmStudent();
                    this.copySubToStudent(sub, student);
                    studentService.insert(student);
                } else {
                    this.copySubToStudent(sub, student);
                    studentService.update(student);
                }
            });
        }
    }

    private void copySubToStudent(GmStudentSub sub, GmStudent student) {
        if (StringUtils.isNotBlank(sub.getClassName())) {
            GmClass gmClass = classService.findByName(sub.getClassName());
            if (ObjectUtils.isEmpty(gmClass)) {
                //2018级1班
                String[] strs = sub.getClassName().split("级");
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int strYear = Integer.valueOf(strs[0]);
                int diff = year - strYear;
                String c = strs[1].substring(0, 1);
                String[] cs = new String[]{"一", "二", "三"};

                String className = cs[diff] + "（" + c + "）班";

                int classId = classService.init(className, strYear);
                student.setClassId(classId);
            } else {
                student.setClassId(gmClass.getId());
            }
        }
        if (StringUtils.isNotBlank(sub.getName()))
            student.setName(sub.getName());
        if (StringUtils.isNotBlank(sub.getStudentNo()))
            student.setStudentNo(sub.getStudentNo());
        if (StringUtils.isNotBlank(sub.getSex())) {
            String sex = Objects.equals("男", sub.getSex()) ? "1" : "2";
            student.setSex(sex);
        }
        if (StringUtils.isNotBlank(sub.getBirthday()))
            student.setBirthday(DateUtil.parse(sub.getBirthday(), "yyyy-MM-dd"));

    }
}
