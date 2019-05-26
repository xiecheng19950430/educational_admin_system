package com.ebay.services;

import com.ebay.mappers.GmStudentAttendanceMapper;
import com.ebay.models.GmStudentAttendance;
import com.ebay.templete.StudentAttendanceTemplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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

    public void excelImport(MultipartFile file) {
        List<GmStudentAttendance> attendances = StudentAttendanceTemplete.temp(file);
        if (!CollectionUtils.isEmpty(attendances)) {
            attendances.forEach(attendance -> {
                boolean isHas = this.checkWithOutSelf(attendance.getStudentNo(), attendance.getSemester(), null);
                if (!isHas) this.insert(attendance);
            });
        }
    }

    public GmStudentAttendance findByNoAndSemester(String studentNo, String semester) {
        return mapper.findByNoAndSemester(studentNo, semester);
    }
}
