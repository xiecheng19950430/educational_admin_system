package com.ebay.services;

import com.ebay.common.utils.BeanUtil;
import com.ebay.mappers.GmStudentAttendanceMapper;
import com.ebay.models.GmStudentAttendance;
import com.ebay.templete.StudentAttendanceTemplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class GmStudentAttendanceService {
    @Autowired
    private GmStudentAttendanceMapper mapper;

    public int insert(GmStudentAttendance attendance) {
        return mapper.insert(attendance);
    }

    public boolean checkWithOutSelf(String studentNo, String semester, Integer id) {
        return mapper.checkWithOutSelf(studentNo, semester, id);
    }

    public GmStudentAttendance findByNoAndSemester(String studentNo, String semester) {
        return mapper.findByNoAndSemester(studentNo, semester);
    }


    public int update(GmStudentAttendance attendance) {
        return mapper.update(attendance);
    }

    public void excelImport(MultipartFile file) {
        List<GmStudentAttendance> attendances = StudentAttendanceTemplete.temp(file);
        if (!CollectionUtils.isEmpty(attendances)) {
            attendances.forEach(attendance -> {
                GmStudentAttendance old = this.findByNoAndSemester(attendance.getStudentNo(), attendance.getSemester());
                if (ObjectUtils.isEmpty(old)) {
                    this.insert(attendance);
                } else {
                    BeanUtil.copyNotNullBean(attendance, old);
                    this.update(old);
                }
            });
        }
    }

}
