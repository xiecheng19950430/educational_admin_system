package com.ebay.mappers;

import com.ebay.models.GmStudentAttendance;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GmStudentAttendanceMapper {
    int insert(GmStudentAttendance attendance);

    boolean checkWithOutSelf(@Param("studentNo")String studentNo, @Param("semester")String semester, @Param("id")Integer id);

    @Select("select * from gm_student_attendance where studentNo=#{studentNo} and semester=#{semester}")
    GmStudentAttendance findByNoAndSemester(String studentNo, String semester);

    int update(GmStudentAttendance attendance);
}
